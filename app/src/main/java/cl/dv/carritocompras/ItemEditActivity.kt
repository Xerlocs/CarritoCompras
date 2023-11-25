package cl.dv.carritocompras

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import cl.dv.carritocompras.database.entities.QuoteEntity

class ItemEditActivity : AppCompatActivity(){

    private lateinit var editTextNombre: EditText
    private lateinit var editTextTipo: EditText
    private lateinit var editTextCantidad: EditText
    private lateinit var editTextPrecio: EditText
    private lateinit var buttonSaveChanges: Button

    private lateinit var item: QuoteEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_edit)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextTipo = findViewById(R.id.editTextTipo)
        editTextCantidad = findViewById(R.id.editTextCantidad)
        editTextPrecio = findViewById(R.id.editTextPrecio)
        buttonSaveChanges = findViewById(R.id.buttonSave)

        item = intent.getParcelableExtra("item")!!

        editTextNombre.setText(item.nombre)
        editTextTipo.setText(item.tipo)
        editTextCantidad.setText(item.cantidad.toString())
        editTextPrecio.setText(item.precio.toString())

        buttonSaveChanges.setOnClickListener {
            // Retrieve and update user input
            val updateNombre = editTextNombre.text.toString()
            val updatedTipo = editTextTipo.text.toString()
            val updatedCantidad = editTextCantidad.text.toString().toIntOrNull() ?: 0
            val updatedPrecio = editTextPrecio.text.toString().toIntOrNull() ?: 0 // Default to 0 if parsing fails

            // Update the patient object with the edited information
            item = item.copy(
                nombre = updateNombre,
                tipo = updatedTipo,
                cantidad = updatedCantidad,
                precio = updatedPrecio
            )

            // Pass the updated patient object back to the previous activity
            val resultIntent = Intent()
            resultIntent.putExtra("position",intent.getIntExtra("position",0))
            setResult(RESULT_OK, resultIntent)
            finish() // Close the editing activity and return to the previous activity
        }
    }

}