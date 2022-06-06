package com.example.storev2

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.storev2.databinding.ItemRecyclerviewRegistroBinding

class RecyclerViewRegistroAdapter : RecyclerView.Adapter<RecyclerViewRegistroAdapter.ViewHolder>(){

    lateinit var context : Context
    lateinit var cursor : Cursor

    fun RecyclerViewRegistroAdapter(context : Context, cursor : Cursor){
        this.context = context
        this.cursor = cursor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_recyclerview_registro, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)

        holder.txId.text = cursor.getInt(0).toString()
        holder.txNombre.text = cursor.getString(1)
        holder.txCantidad.text = cursor.getString(2)
        holder.txPrecio.text = cursor.getString(3)
        holder.txTotal.text = cursor.getString(4)
    }

    override fun getItemCount(): Int {
        if (cursor == null)
            return 0
        else
            return cursor.count
    }

    inner class ViewHolder: RecyclerView.ViewHolder{

        val txNombre: TextView
        val txId: TextView
        val txCantidad: TextView
        val txPrecio: TextView
        val txTotal: TextView

        constructor(view: View): super(view){
            val bindingItems = ItemRecyclerviewRegistroBinding.bind(view)
            txNombre = bindingItems.registroProducto
            txId = bindingItems.registroId
            txCantidad = bindingItems.registroCantidad
            txPrecio = bindingItems.registroPrecio
            txTotal = bindingItems.registroTotal
        }
    }



}