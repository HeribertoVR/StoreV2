package com.example.storev2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView

class Login : AppCompatActivity() {

    private lateinit var etNombreUsuario : EditText
    private lateinit var etContrasenaUsuario : EditText
    private lateinit var etBtnValid : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etNombreUsuario = findViewById(R.id.validUser)
        etContrasenaUsuario = findViewById(R.id.validPassword)
        etBtnValid = findViewById(R.id.validButton)

        etBtnValid.setOnClickListener{

            val nombreUsuario = etNombreUsuario.text.toString().trim()
            val contrasenaUsuario = etContrasenaUsuario.text.toString().trim()

            if(nombreUsuario.isEmpty()){
                etNombreUsuario.error = "Nombre de Usuario Requerido"
                return@setOnClickListener

            }else if (contrasenaUsuario.isEmpty()){
                etContrasenaUsuario.error = "Contrasena de Usuario Requerida"
                return@setOnClickListener

            }else if (nombreUsuario=="usuario" && contrasenaUsuario=="123"){
                startActivity(Intent(this@Login, MainActivity::class.java))
                etContrasenaUsuario.text.clear()
                etNombreUsuario.text.clear()

            }else if (nombreUsuario!="usuario"){
                etNombreUsuario.error = "Nombre de Usuario Incorrecto"
                return@setOnClickListener

            }else if (contrasenaUsuario!="123"){
                etContrasenaUsuario.error = "Contraseña Incorrecta"
                return@setOnClickListener
            }
        }

    }
}