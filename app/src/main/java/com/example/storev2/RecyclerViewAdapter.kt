package com.example.storev2

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.storev2.databinding.ItemRecyclerviewBinding

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    lateinit var context : Context
    lateinit var cursor : Cursor

    fun RecyclerViewAdapter(context : Context, cursor : Cursor){
        this.context = context
        this.cursor = cursor
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_recyclerview,
            parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)

        holder.tvProducto.text =cursor.getString(1)
        holder.tvCodigo.text =cursor.getString(2)
        holder.tvCategoria.text =cursor.getString(3)
        holder.tvPrecio.text =cursor.getString(4)
        holder.tvCantiad.text =cursor.getString(5)
        //holder.tvDescripcion.text =cursor.getString(6)


    }

    override fun getItemCount(): Int {
        if (cursor == null)
            return 0
        else
            return cursor.count
    }


    inner class ViewHolder: RecyclerView.ViewHolder{


        val tvProducto: TextView
        val tvCodigo: TextView
        val tvCategoria: TextView
        val tvPrecio: TextView
        val tvCantiad: TextView
       // val tvDescripcion: TextView





        constructor(view: View) : super (view){
            val bindingItemsRV = ItemRecyclerviewBinding.bind(view)
            tvProducto = bindingItemsRV.valorProducto
            tvCodigo = bindingItemsRV.valorCodigo
            tvCategoria = bindingItemsRV.valorTipo
            tvPrecio = bindingItemsRV.valorPrecio
            tvCantiad = bindingItemsRV.valorCantidad
            //tvDescripcion = bindingItemsRV.tvItemDescripcion

            view.setOnClickListener{
                Toast.makeText(context, "Producto: ${tvProducto.text}, Codigo: ${tvCodigo.text}, " +
                        "Tipo: ${tvCategoria.text}, Precio: ${tvPrecio.text},  Cantidad: ${tvCantiad.text}",
                Toast.LENGTH_SHORT).show()
            }

        }
    }


}