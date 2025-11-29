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



class SecondActivity2 : AppCompatActivity() {
    private val products = listOf(
        Product(1, "Морковь",  50.0,  "Просто морковка", R.drawable.one),
        Product(2, "Дикий огурец",  60.0,  "Огурец Дикий огурец", R.drawable.two),
        Product(3, "Банан",   70.0, "Просто банан", R.drawable.three),
        Product(4, "Яблоко",  80.0,  "Просто яблоко", R.drawable.four),
        Product(5, "Арбуз", 90.0,  "Просто арбуз", R.drawable.five),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second2)

        val container = findViewById<ListView>(R.id.lvCatalog)

        val adapter = ProductAdapter(this, products)

        container.adapter = adapter



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