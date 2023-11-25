package cl.dv.carritocompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import cl.dv.carritocompras.adapter.UserNewDialog
import cl.dv.carritocompras.database.QuoteDatabase
import cl.dv.carritocompras.database.entities.QuoteEntity

class MainActivity : AppCompatActivity() {

    private lateinit var cambio: TextView
    private lateinit var db: QuoteDatabase
    private lateinit var listItems: MutableList<QuoteEntity>
    private lateinit var listViewItems: ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        listViewItems = findViewById(R.id.listViewItems)
        cambio = findViewById(R.id.orden)

        db = Room.databaseBuilder(
            applicationContext,
            QuoteDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        listItems = mutableListOf(
            QuoteEntity(0, "Coca-Cola 3lt", "Bebestible", 1, 3000)
        )

        val list: List<QuoteEntity> = db.getQuoteDao().getAllQuotes()
        listItems = list.toMutableList()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems.map{it.nombre})
        Toast.makeText(this, "Hi: "+ listItems.size.toString(), Toast.LENGTH_LONG).show()

        listViewItems.adapter = adapter

        list.size

        registerForContextMenu(listViewItems)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_order -> {
                if(cambio.text == "A - Z"){
                    cambio.setText(R.string.Por_Cantida)
                }else if(cambio.text == "Cantidad de producto"){
                    cambio.setText(R.string.Por_Tipo)
                }else{
                    cambio.setText(R.string.A_Z)
                }
                return true
            }
            R.id.action_add -> {
                val list: List<QuoteEntity> = db.getQuoteDao().getAllQuotes()
                val dialog = UserNewDialog(this,list.size+1,this)
                dialog.show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.item_list_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_edit -> {
                // Handle the "Edit" option
                //val intent = Intent(this, PatientEditActivity::class.java)
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                //intent.putExtra("patient",patients.get(position))
                //intent.putExtra("position",position)
                //startActivityForResult(intent, PatientListActivity.REQUEST_EDITER)
                true
            }
            R.id.action_delete -> {
                // Handle the "Delete" option
                // Show the confirmation dialog when "Delete" is selected
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val position = info.position
                showDeleteConfirmationDialog(position)
                true
            }
            // Add cases for other options as needed
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    private fun showDeleteConfirmationDialog(itemPosition: Int) {
        val builder = AlertDialog.Builder(this)

        builder.setMessage(R.string.message_delete_item)
        builder.setPositiveButton(R.string.borrar) { dialog, _ ->
            // Handle the delete action here
            deleteItem(itemPosition)
        }
        builder.setNegativeButton(R.string.cancelar) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun deleteItem(itemPosition: Int) {

        deleteFromDatabase(listItems[itemPosition])
        refreshFromDatabase()
    }

    fun deleteFromDatabase(user : QuoteEntity) {
        db.getQuoteDao().delete(user)
    }

    fun refreshFromDatabase(){
        val list: List<QuoteEntity> = db.getQuoteDao().getAllQuotes()
        Toast.makeText(this,"Hi: "+list.size.toString(),Toast.LENGTH_LONG).show()

        listItems = list.toMutableList()
        Toast.makeText(this,"Hi: "+listItems.size.toString(),Toast.LENGTH_LONG).show()

        listViewItems.invalidate()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems.map { it.nombre })
        listViewItems.adapter = adapter
    }
}