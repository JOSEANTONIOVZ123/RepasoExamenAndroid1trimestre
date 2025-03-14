package com.example.repasoexamenandroid.contactos

data class Contacto(
    var id: Int,
    var nombre: String,
    var telefono: String,
    var email: String,
    val imagen: Int
)