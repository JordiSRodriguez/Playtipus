package com.example.playtipus

class Persona(val persona1: String) {

    var nombreCompleto: String = ""
        get() = field
        set(value) {
            field = value
        }

    var nombreUsuario: String = ""
        get() = field
        set(value) {
            field = value
        }

    var email: String = ""
        get() = field
        set(value) {
            field = value
        }

    var password: String = ""
        get() = field
        set(value) {
            field = value
        }

    constructor(nombreCompleto: String, nombreUsuario: String, email: String, password:String) : this(nombreCompleto) {
        this.nombreCompleto = nombreCompleto
        this.nombreUsuario = nombreUsuario
        this.email = email
        this.password = password
    }
}