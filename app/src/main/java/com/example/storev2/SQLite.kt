package com.example.storev2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLite (context: Context) : SQLiteOpenHelper(context, "elementos.db", null, 1){

    //orden para crear la tabla elementos
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE elementos " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "producto TEXT, codigo INTEGER, categoria TEXT, precio INTEGER, cantidad INTEGER, descripcion TEXT)"
        db!!.execSQL(ordenCreacion)

    }

    //orden para actualizar la tabla de elementos
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS elementos"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    //funcion para ayadir un dato a la tabla elementos
    fun anyadirDato(producto:String, codigo:Int, categoria:String, precio:Int, cantidad:Int, descripcion:String){
        val datos = ContentValues()
        datos.put("producto",producto)
        datos.put("codigo", codigo)
        datos.put("categoria", categoria)
        datos.put("precio", precio)
        datos.put("cantidad", cantidad)
        datos.put("descripcion", descripcion)

        val db = this.writableDatabase
        db.insert("elementos",null, datos)
        db.close()

    }

    //funcion para borrar datos a la tabla elementos a traves del id del elemento a eliminar
    fun borrarDato(id: Int) : Int{
        val args = arrayOf(id.toString())

        val db = this.writableDatabase
        val borrados = db.delete("elementos","_id = ?", args)
        //db.execSQL("DELETE FROM amigos WHERE id = ?", args)
        db.close()
        return borrados

    }

    //funcion para modificar datos de la tabla elementos a traves del id del elemento asignado
    fun modificarDato(id: Int, producto:String, codigo: Int, categoria:String, precio:Int, cantidad:Int, descripcion:String){
        val args = arrayOf(id.toString())

        val datos = ContentValues()
        datos.put("producto",producto)
        datos.put("codigo", codigo)
        datos.put("categoria", categoria)
        datos.put("precio", precio)
        datos.put("cantidad", cantidad)
        datos.put("descripcion", descripcion)

        val db = this.writableDatabase
        db.update("elementos", datos,"_id = ?", args)
        db.close()

    }

}