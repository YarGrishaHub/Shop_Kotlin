package com.example.myapplicationshop

import Product
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.model.FavoriteStorage

class FavoriteAdapter (
    private val items: MutableList<Product>
) : RecyclerView.Adapter<FavoriteAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById<ImageView>(R.id.ivProductImage)
        val name: TextView = view.findViewById<TextView>(R.id.tvProductName)
        val price: TextView = view.findViewById<TextView>(R.id.tvProductPrice)
        val button: TextView = view.findViewById<Button>(R.id.btnDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return VH(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val product = items[position]

        holder.image.setImageResource(product.ImageRes)
        holder.name.text = product.name
        holder.price.text = "${product.price} $"

        holder.button.visibility = View.GONE

        // обработка клика по всей карточке
        holder.itemView.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Удаление товара")
                .setMessage("Удалить товар из избранного?")
                .setPositiveButton("Да"){_,_ ->
                    val currentPosition = holder.adapterPosition // индекс эл-а по которому нажали
                    if (currentPosition != RecyclerView.NO_POSITION){
                        val itemToRemove = items[currentPosition] // получили сам эл по индексу
                        FavoriteStorage.remove(holder.itemView.context, itemToRemove) // удалили из object
                        items.removeAt(currentPosition) // удалили из текущего списка
                        notifyItemRemoved(currentPosition) // перерисовали RV
                    }
                }
                .setNegativeButton("Нет", null)
                .show()
        }
    }



}