package cl.dv.carritocompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.room.Room
import cl.dv.carritocompras.database.QuoteDatabase
import cl.dv.carritocompras.database.entities.QuoteEntity

private lateinit var cambio: TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val db = Room.databaseBuilder(
            applicationContext,
            QuoteDatabase::class.java, "database-name"
        ).build()

        val userDao = db.getQuoteDao()
        //val users: List<QuoteEntity> = userDao.getAllQuotes()

        cambio = findViewById(R.id.orden)
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
                val intentAdd = Intent(this, CrearActivity::class.java)
                startActivity(intentAdd)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}