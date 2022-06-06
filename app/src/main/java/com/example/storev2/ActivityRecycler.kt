package com.example.storev2

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storev2.databinding.ActivityRecyclerBinding

class ActivityRecycler : AppCompatActivity(){

    private lateinit var binding: ActivityRecyclerBinding
    private lateinit var amigosDBHelper: SQLite
    private lateinit var db :SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        amigosDBHelper = SQLite(this)

        db = amigosDBHelper.readableDatabase
        val cursor : Cursor = db.rawQuery("SELECT * FROM elementos", null)

        val adaptador = RecyclerViewAdapter()
        adaptador.RecyclerViewAdapter(this, cursor)

        binding.rvDatos.setHasFixedSize(true)
        binding.rvDatos.layoutManager = LinearLayoutManager(this)
        binding.rvDatos.adapter = adaptador



    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}