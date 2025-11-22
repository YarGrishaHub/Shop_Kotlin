package com.example.myapplicationshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import Product
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView

private val SecondActivity2.lvCatalog: Int

class SecondActivity2 : AppCompatActivity() {
    private val products = listOf(
        Product(1, "Кольцо 1",  50.0,  "Описание 1", R.drawable.one),
        Product(2, "Кольцо 2",  60.0,  "Описание 2", R.drawable.two),
        Product(3, "Кольцо 3",   70.0, "Описание 3", R.drawable.three),
        Product(4, "Кольцо 4",  80.0,  "Описание 4", R.drawable.four),
        Product(5, "### 5", 90.0,  "Описание 5", R.drawable.five),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second2)

        val container = findViewById<ListView>(lvCatalog)


//        val container = findViewById<LinearLayout>(R.id.catalogContainer)
//
//
//        products.forEach { product ->
//            val view = layoutInflater.inflate(R.layout.item_product, container, false)
//
//            view.findViewById<ImageView>(R.id.ivProductImage).setImageResource(product.ImageRes)
//            view.findViewById<TextView>(R.id.tvProductName).text = product.name
//            view.findViewById<TextView>(R.id.tvProductPrice).text = "${product.price} $"
//
//            view.findViewById<Button>(R.id.btnDetails).setOnClickListener {
//                val intent = Intent(this, DetailActivity::class.java).apply {
//                    putExtra("name", product.name)
//                    putExtra("price", product.price)
//                    putExtra("ImageRes", product.ImageRes)
//                    putExtra("description", product.description)
//                }
//                startActivity(intent)
//            }
//
//            container.addView(view)
//        }

    }
}


class ProductAdapter(
    private val context: android.content.Context,
    private val products: List<Product>
) : android.widget.BaseAdapter(){

    override fun getCount() = products.size

    override fun getItem(position: Int) = products[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_product, parent, false)

        val product = getItem(pos)

        val image = view.findViewById<ImageView>(R.id.ivProductImage)
        val name = view.findViewById<TextView>(R.id.tvProductName)
        val price = view.findViewById<TextView>(R.id.tvProductPrice)
        val button = view.findViewById<Button>(R.id.btnDetails)

        image.setImageResource(product.ImageRes)
            name.text = product.name
            price.text = "${product.price} $"

        button.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("name", product.name)
                putExtra("price", product.price)
                putExtra("ImageRes", product.ImageRes)
                putExtra("description", product.description)
            }
            context.startActivity(intent)
        }
    return view
    }

}