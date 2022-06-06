package com.example.storev2

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storev2.databinding.ActivityRecyclerRegistroBinding

class ActivityRecyclerRegistro : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerRegistroBinding
    private lateinit var registroDB : SQLiteRegistro
    private lateinit var bd: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registroDB = SQLiteRegistro(this)

        bd = registroDB.readableDatabase
        val cursor : Cursor = bd.rawQuery( "SELECT * FROM registros", null)

        val adaptador = RecyclerViewRegistroAdapter()
        adaptador.RecyclerViewRegistroAdapter(this, cursor)

        binding.registrosDatos.setHasFixedSize(true)
        binding.registrosDatos.layoutManager = LinearLayoutManager(this)
        binding.registrosDatos.adapter = adaptador
    }

    override fun onDestroy() {
        super.onDestroy()
        bd.close()
    }
}