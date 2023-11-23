package cl.dv.carritocompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

private lateinit var cambio: TextView
private lateinit var menu_orden: ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        menu_orden = findViewById(R.id.Menu_ordenar)

        cambio = findViewById(R.id.aaa)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_order -> {
                cambio.setText(R.string.cambio)
                menu_orden.visibility = View.VISIBLE
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