package cl.dv.carritocompras.adapter

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import cl.dv.carritocompras.R
import cl.dv.carritocompras.database.entities.QuoteEntity

class ItemDetailDialog(
    context: Context,
    private val item: QuoteEntity
): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_item_detail)

        val textViewNombre = findViewById<TextView>(R.id.textViewNombre)
        val textViewTipo = findViewById<TextView>(R.id.textViewTipo)
        val textViewCantidad = findViewById<TextView>(R.id.textViewCantidad)
        val textViewPrecio = findViewById<TextView>(R.id.textViewPrecio)
        val buttonGoBack = findViewById<Button>(R.id.buttonAddAndGoBack)

        textViewNombre.text = item.nombre
        textViewTipo.text = item.tipo
        textViewCantidad.text = item.cantidad.toString()
        textViewPrecio.text = item.precio.toString()

        buttonGoBack.setOnClickListener {
            dismiss()
        }
    }
}