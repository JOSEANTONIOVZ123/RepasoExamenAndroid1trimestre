package com.example.repasoexamenandroid.contactos

import android.os.Handler
import android.os.Looper
import com.example.repasoexamenandroid.R
import kotlinx.coroutines.*

object ContactoProvider {
    val listaContactos = mutableListOf(
        Contacto(1, "Juan Pérez", "654 321 987", "juan.perez@email.com", R.drawable.foto10),
        Contacto(2, "María López", "678 456 123", "maria.lopez@email.com", R.drawable.foto12),
        Contacto(3, "Carlos Sánchez", "689 789 456", "carlos.sanchez@email.com", R.drawable.foto3),
        Contacto(4, "Ana García", "612 345 678", "ana.garcia@email.com", R.drawable.foto7)
    )

}
