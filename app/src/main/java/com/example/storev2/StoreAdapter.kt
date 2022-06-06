package com.example.storev2

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.storev2.databinding.ActivityStoreBinding
import org.w3c.dom.Text

class StoreAdapter(val c:Context, val userList:ArrayList<StoreData>):RecyclerView.Adapter<StoreAdapter.UserViewHolder>() {

    inner class UserViewHolder(val v:View):RecyclerView.ViewHolder(v){
        var name:TextView
        var mbNum:TextView
        var cost: TextView
        var mMenus:ImageView

        init {
            name = v.findViewById<TextView>(R.id.mTitle)
            mbNum = v.findViewById<TextView>(R.id.mSubtitle)
            cost = v.findViewById<TextView>(R.id.mCost)
            mMenus = v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener{ popupMenus(it)}
        }

        private fun popupMenus(v:View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText->{
                        val v = LayoutInflater.from(c).inflate(R.layout.add_item, null)
                        val name = v.findViewById<EditText>(R.id.userName)
                        val number = v.findViewById<EditText>(R.id.userNo)
                        val cost = v.findViewById<EditText>(R.id.userCost)
                        val total = v.findViewById<TextView>(R.id.textViewTotal)
                                AlertDialog.Builder(c)
                                    .setView(v)
                                    .setPositiveButton("OK"){
                                        dialog,_->



                                        position.userName = "Nombre: "+name.text.toString()
                                        position.userMb = "Cantidad: "+number.text.toString()
                                        position.userCO = "Costo: "+cost.text.toString()
                                        notifyDataSetChanged()
                                        Toast.makeText(c, "Los Datos Fueron Editados",Toast.LENGTH_SHORT).show()
                                        dialog.dismiss()

                                    }
                                    .setNegativeButton("Cancelar"){
                                            dialog,_->
                                            dialog.dismiss()
                                    }
                                    .create()
                                    .show()

                        true
                    }
                    R.id.delete->{
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Estas Seguro de Eliminar Estos Datos?")
                            .setPositiveButton("Yes"){
                                dialog,_->
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c, "Datos Eliminados",Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                    dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else->true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu, true)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_store, parent, false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
        holder.mbNum.text = newList.userMb
        holder.cost.text = newList.userCO
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}