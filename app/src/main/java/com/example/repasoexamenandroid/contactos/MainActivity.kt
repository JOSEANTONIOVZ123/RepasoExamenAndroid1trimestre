package com.example.repasoexamenandroid.contactos

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.repasoexamenandroid.R


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var contactoAdapter: ContactoAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var contactos = mutableListOf<Contacto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerViewContactos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            recargarLista()
            swipeRefreshLayout.isRefreshing = false
        }


        cargarContactos()
    }

    private fun cargarContactos() {
        contactos = ContactoProvider.listaContactos.toMutableList()
        contactoAdapter = ContactoAdapter(contactos, this)
        recyclerView.adapter = contactoAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_recargar -> {
                recargarLista()
                true
            }
            R.id.menu_limpiar -> {
                confirmarEliminarLista()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recargarLista() {
        cargarContactos()
        contactoAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Lista recargada", Toast.LENGTH_SHORT).show()
    }

    private fun confirmarEliminarLista() {
        AlertDialog.Builder(this)
            .setTitle("Eliminar todos los contactos")
            .setMessage("¿Seguro que quieres borrar la lista completa?")
            .setPositiveButton("Sí") { _, _ -> limpiarLista() }
            .setNegativeButton("No", null)
            .show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun limpiarLista() {
        contactos.clear()
        contactoAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Lista vaciada", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        recargarLista() // Fuerza la actualización cuando volvemos a MainActivity
    }





}
