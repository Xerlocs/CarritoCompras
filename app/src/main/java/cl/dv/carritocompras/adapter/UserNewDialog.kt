package cl.dv.carritocompras.adapter

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.room.Room
import cl.dv.carritocompras.MainActivity
import cl.dv.carritocompras.R
import cl.dv.carritocompras.database.QuoteDatabase
import cl.dv.carritocompras.database.entities.QuoteEntity

class UserNewDialog(
    context: Context,
    idItem: Int,
    act: MainActivity
): Dialog(context){

    private lateinit var nombre: EditText
    private lateinit var tipo: EditText
    private lateinit var cantidad: EditText
    private lateinit var precio: EditText
    private lateinit var db: QuoteDatabase
    private var id: Int = idItem
    private var act: MainActivity = act

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_user_new)

        nombre = findViewById(R.id.textInputNombre)
        tipo = findViewById(R.id.textInputTipo)
        cantidad = findViewById(R.id.textInputCantidad)
        precio = findViewById(R.id.textInputPrecio)

        db = Room.databaseBuilder(
            context,
            QuoteDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        val buttonAddAndGoBack: Button = findViewById(R.id.buttonAddAndGoBack)
        buttonAddAndGoBack.setOnClickListener{

            db.getQuoteDao().insertAll(
                QuoteEntity(id, nombre.text.toString(), tipo.text.toString(), cantidad.text.toString().toInt(), precio.text.toString().toInt())
            )
            act.refreshFromDatabase()
            dismiss()
        }

    }
}