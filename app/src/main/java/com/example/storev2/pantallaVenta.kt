package com.example.storev2

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.storev2.databinding.ActivityPantallaVentaBinding

class pantallaVenta : AppCompatActivity() {

    lateinit var binding: ActivityPantallaVentaBinding
    lateinit var elementosDBHelper: SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPantallaVentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        elementosDBHelper = SQLite(this)

        //Asignacion de contenido para el boton de Guardar un datos en la tabla  de datos elementos
        binding.btGuardar.setOnClickListener {

            //Condicion para que el boton de btGuardar no guarde datos en blanco
            if (binding.etProducto.text.isNotBlank() && binding.etCodigo.text.isNotBlank() && binding.etCategoria.text.isNotBlank() &&
                binding.etPrecio.text.isNotBlank() && binding.etCantidad.text.isNotBlank() && binding.etDescripcion.text.isNotBlank()){

                elementosDBHelper.anyadirDato(binding.etProducto.text.toString(), binding.etCodigo.text.toString().toInt(),
                    binding.etCategoria.text.toString(), binding.etPrecio.text.toString().toInt(), binding.etCantidad.text.toString().toInt(),
                    binding.etDescripcion.text.toString())

                //se limpian los textos para introducir datos nuevos
                binding.etProducto.text.clear()
                binding.etCodigo.text.clear()
                binding.etCategoria.text.clear()
                binding.etPrecio.text.clear()
                binding.etCantidad.text.clear()
                binding.etDescripcion.text.clear()

                //mensaje que indica que se a podido guardar en la base de datos
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
            }
            else{
                //mensaje que indica que no se a podido guardar en la base de datos
                Toast.makeText(this, "No se ha podido guardar", Toast.LENGTH_LONG).show()
            }
        }

        //Contenido para al presionar el boton btConsultar se puedan visualizar los datos guardados en la base de datos
        binding.btConsultar.setOnClickListener {
            binding.tvConsulta.text = ""
            val db : SQLiteDatabase = elementosDBHelper.readableDatabase
            val cursor =db.rawQuery("SELECT * FROM elementos", null)

            //Se muestran los datos de la tabla de datos en un textView
            if(cursor.moveToFirst()){
                do{
                    binding.tvConsulta.append(
                        cursor.getInt(0).toString() + ": ")
                    binding.tvConsulta.append(
                        cursor.getString(1).toString() + ", ")
                    binding.tvConsulta.append(
                        cursor.getInt(2).toString() + ", ")
                    binding.tvConsulta.append(
                        cursor.getString(3).toString() + ", ")
                    binding.tvConsulta.append(
                        cursor.getInt(4).toString() + ", ")
                    binding.tvConsulta.append(
                        cursor.getInt(5).toString() + ", ")
                    binding.tvConsulta.append(
                        cursor.getString(6).toString() + "\n ")
                }while (cursor.moveToNext())
            }
        }

        //Contenido para que el boton btBorrar pueda borrar datos a traves de la Id del elemento que se desea eliminar
        binding.btBorrar.setOnClickListener {
            var cantidad = 0

            if (binding.etId.text.isNotBlank()){
                cantidad = elementosDBHelper.borrarDato(binding.etId.text.toString().toInt())
                binding.etId.text.clear()
            }
            else{
                Toast.makeText(this, "Datos Borrados: $cantidad", Toast.LENGTH_LONG).show()
            }

        }

        //Contenido para que el boton btEditar pueda modificar datos a traves de la Id del elemento que se desea modificar
        binding.btEditar.setOnClickListener {

            if (binding.etProducto.text.isNotBlank() && binding.etCodigo.text.isNotBlank() && binding.etId.text.isNotBlank() &&
                binding.etCategoria.text.isNotBlank() && binding.etPrecio.text.isNotBlank() &&
                binding.etCantidad.text.isNotBlank() && binding.etDescripcion.text.isNotBlank()){

                elementosDBHelper.modificarDato(binding.etId.text.toString().toInt(),binding.etProducto.text.toString(),
                    binding.etCodigo.text.toString().toInt(), binding.etCategoria.text.toString(), binding.etPrecio.text.toString().toInt(),
                    binding.etCantidad.text.toString().toInt(), binding.etDescripcion.text.toString())

                //Se limpian los EditText para la siguiente edicion de un elemento
                binding.etProducto.text.clear()
                binding.etCodigo.text.clear()
                binding.etId.text.clear()
                binding.etCategoria.text.clear()
                binding.etPrecio.text.clear()
                binding.etCantidad.text.clear()
                binding.etDescripcion.text.clear()

                Toast.makeText(this, "Modificado", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "No se ha podido modificar", Toast.LENGTH_LONG).show()
            }
        }

    }
}