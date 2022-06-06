package com.example.storev2

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonVenta = findViewById<CardView>(R.id.cardVenta)
        buttonVenta.setOnClickListener{
            startActivity(Intent(this@MainActivity, StoreActivity::class.java))
        }

        val buttonAgregar = findViewById<CardView>(R.id.cardAgregar)
        buttonAgregar.setOnClickListener{
            startActivity(Intent(this@MainActivity, pantallaVenta::class.java))
        }

        val buttonInventario = findViewById<CardView>(R.id.cardInventario)
        buttonInventario.setOnClickListener{
            startActivity(Intent(this@MainActivity, ActivityRecycler::class.java))
        }

        val buttonSalir = findViewById<CardView>(R.id.cardSalir)
        buttonSalir.setOnClickListener{
            finish()
        }

        val btnRegistro = findViewById<CardView>(R.id.cardRegistro)
        btnRegistro.setOnClickListener{
            startActivity(Intent(this@MainActivity, ActivityRecyclerRegistro::class.java))
        }

    }
}