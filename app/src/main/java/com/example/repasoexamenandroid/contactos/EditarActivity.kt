package com.example.repasoexamenandroid.contactos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.repasoexamenandroid.R

class EditarActivity : AppCompatActivity() {

    private lateinit var imageViewEditar: ImageView
    private lateinit var editTextNombre: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonGuardar: Button
    private var contactoId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        imageViewEditar = findViewById(R.id.imageViewEditar)
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonGuardar = findViewById(R.id.buttonGuardar)

        contactoId = intent.getIntExtra("id_contacto", -1)
        if (contactoId != -1) {
            val contacto = ContactoProvider.listaContactos[contactoId]
            imageViewEditar.setImageResource(contacto.imagen)
            editTextNombre.setText(contacto.nombre)
            editTextTelefono.setText(contacto.telefono)
            editTextEmail.setText(contacto.email)
        }

        buttonGuardar.setOnClickListener {
            val nuevoNombre = editTextNombre.text.toString().trim()
            val nuevoTelefono = editTextTelefono.text.toString().trim()
            val nuevoEmail = editTextEmail.text.toString().trim()

            if (contactoId != -1 && nuevoNombre.isNotEmpty() && nuevoTelefono.isNotEmpty() && nuevoEmail.isNotEmpty()) {
                ContactoProvider.listaContactos[contactoId].nombre = nuevoNombre
                ContactoProvider.listaContactos[contactoId].telefono = nuevoTelefono
                ContactoProvider.listaContactos[contactoId].email = nuevoEmail

                val resultIntent = Intent()
                resultIntent.putExtra("id_contacto", contactoId)
                resultIntent.putExtra("nuevo_nombre", nuevoNombre)
                resultIntent.putExtra("nuevo_telefono", nuevoTelefono)
                resultIntent.putExtra("nuevo_email", nuevoEmail)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
