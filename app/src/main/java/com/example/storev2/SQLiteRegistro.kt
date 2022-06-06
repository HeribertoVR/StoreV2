package com.example.storev2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteRegistro(context: Context) : SQLiteOpenHelper(
    context, "registro.db", null, 1) {

    override fun onCreate(bd: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE registros" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "productos TEXT, cantidad TEXT, precio TEXT, total TEXT)"

        bd!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(bd: SQLiteDatabase?,
                           oldVersion: Int, newVersion: Int) {
        val ordenDeBorrar = "DROP TABLE IF EXISTS registros"
        bd!!.execSQL(ordenDeBorrar)
        onCreate(bd)
    }

    fun agregarDato(productos: String, cantidad: String, precio: String, total: String){
        val dato = ContentValues()
        dato.put("productos", productos)
        dato.put("cantidad", cantidad)
        dato.put("precio", precio)
        dato.put("total", total)

        val bd = this.writableDatabase
        bd.insert("registros",null, dato)
        bd.close()
    }

    fun eleminarDato( id: Int) : Int{
        val argumento = arrayOf(id.toString())

        val bd = this.writableDatabase
        val eliminar = bd.delete("registros","_id = ?", argumento)
        bd.close()
        return eliminar
    }

    fun alterarDato(id: Int, productos: String, cantidad: String, precio: String, total: String){
        val dato = ContentValues()
        val argumento = arrayOf(id.toString())

        dato.put("productos", productos)
        dato.put("cantidad", cantidad)
        dato.put("precio", precio)
        dato.put("total", total)

        val bd = this.writableDatabase
        bd.update("registros", dato,"_id = ?", argumento)
        bd.close()
    }


}