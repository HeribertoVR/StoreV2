package com.example.storev2

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storev2.databinding.ActivityMainBinding
import com.example.storev2.databinding.ActivityStoreBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StoreActivity : AppCompatActivity() {

    private lateinit var addsBtn:FloatingActionButton

    private lateinit var comprarBtn:Button
    private lateinit var eliminarBtn:Button

    private lateinit var recy:RecyclerView
    private lateinit var userList: ArrayList<StoreData>
    private lateinit var storeAdapter:StoreAdapter
    var acum = 0

    //lateinit var binding: ActivityStoreBinding
    lateinit var registrosDBHelper: SQLiteRegistro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        userList = ArrayList()

        addsBtn = findViewById(R.id.addBtn)
        recy = findViewById(R.id.mRecycler)

        comprarBtn = findViewById(R.id.btnComprar)
        eliminarBtn = findViewById(R.id.btnBorrar)

        storeAdapter = StoreAdapter(this, userList)

        recy.layoutManager = LinearLayoutManager(this)
        recy.adapter = storeAdapter

        addsBtn.setOnClickListener { addInfo() }

        //binding = ActivityStoreBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        registrosDBHelper = SQLiteRegistro(this)



        //val valName = name.text.toString().trim()
        //val valMbNum = mbNum.text.toString().trim()
        //val valCost = cost.text.toString().trim()

        comprarBtn.setOnClickListener{

            val name = findViewById<TextView>(R.id.mTitle)
            val mbNum = findViewById<TextView>(R.id.mSubtitle)
            val cost = findViewById<TextView>(R.id.mCost)
            val compraTotal = findViewById<TextView>(R.id.textViewTotal)

            if(compraTotal.text.isNotBlank() && name.text.isNotBlank() && mbNum.text.isNotBlank() && cost.text.isNotBlank()){
                registrosDBHelper.agregarDato( name.text.toString(),mbNum.text.toString(),cost.text.toString(),compraTotal.text.toString())

                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "No se ha Guardado", Toast.LENGTH_LONG).show()
            }
        }

        eliminarBtn.setOnClickListener{
            val cantidad = 0
            val id = findViewById<TextView>(R.id.editId)

            if(id.text.isNotBlank()){
                registrosDBHelper.eleminarDato( id.text.toString().toInt())

                Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(this, "No se ha Eliminado", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item, null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userNo = v.findViewById<EditText>(R.id.userNo)
        val userCost = v.findViewById<EditText>(R.id.userCost)
        val addDialog = AlertDialog.Builder(this)
        val total = findViewById<TextView>(R.id.textViewTotal)




        addDialog.setView(v)

            addDialog.setPositiveButton("Ok") {

                    dialog, _ ->

                val valNumber = userNo.text.toString().trim()
                val valCost = userCost.text.toString().trim()

                if(valNumber.isEmpty()){
                    Toast.makeText(this, "Cantidad de Productos Requerida", Toast.LENGTH_SHORT).show()
                    //userNo.error = "Cantidad de Productos Requerida"
                    //return

                }else if (valCost.isEmpty()){
                    Toast.makeText(this, "Costo de Productos Requerido", Toast.LENGTH_SHORT).show()
                    //userCost.error = "Precio de Productos Requerido"
                    //return

                }else {


                    val names = userName.text.toString()
                    val number = userNo.text.toString()
                    val cost = userCost.text.toString()
                    var resultado = userNo.text.toString().toInt() * userCost.text.toString().toInt()
                    var resultado2 = resultado + acum
                    acum = resultado

                    userList.add(StoreData("Nombre: $names", "Cantidad : $number", "Costo: $cost"))
                    storeAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Agregar", Toast.LENGTH_SHORT).show()
                    total.text = resultado2.toString()

                    dialog.dismiss()
                }
            }


        addDialog.setNegativeButton("Cancel"){
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }


}