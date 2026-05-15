package com.example.myapplicationshop

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import Product
import ProductGridAdapter
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SecondActivity2 : AppCompatActivity() {

    private lateinit var lwList: ListView
    private lateinit var rwGrid: RecyclerView
    private lateinit var listAdapter: ProductAdapter
    private lateinit var gridAdapter: ProductGridAdapter


    private val products = listOf(
        Product(1, "Морковь",  50.0,  "Просто морковка", R.drawable.morkov),
        Product(2, "Огурец",  60.0,  "Огурец Дикий огурец", R.drawable.ogyrez),
        Product(3, "Банан",   70.0, "Просто банан", R.drawable.three),
        Product(4, "Яблоко",  80.0,  "Просто яблоко", R.drawable.four),
        Product(5, "Арбуз", 90.0,  "Просто арбуз", R.drawable.five),
    )

    private val originalList = mutableListOf<Product>()
    private val currentList = mutableListOf<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second2)

        originalList.addAll(products)
        currentList.addAll(products)

        val toolbar = findViewById<Toolbar>(R.id.topBar)
        setSupportActionBar(toolbar)

//      1. Находим оба списка на экране
        lwList = findViewById(R.id.lvCatalog)
        rwGrid = findViewById(R.id.rvCatalogGrid)

//      2. Находим адаптер для LW

        listAdapter = ProductAdapter(this, currentList)

//      3. Находим адаптер для RW

        gridAdapter = ProductGridAdapter(this, currentList)

//      4. Соединяем адаптер и список RW

        lwList.adapter = listAdapter

//       5. Соединяем адаптер и список RW

        rwGrid.layoutManager = GridLayoutManager(this, 2)
        rwGrid.adapter = gridAdapter

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val isGridPref = prefs.getBoolean("isGrid", true)

        if (isGridPref == true) {
            showgrid()
        } else {
            showList()
        }
    }

        private fun showList(){
            lwList.visibility = View.VISIBLE
            rwGrid.visibility = View.GONE
            val prefs = getSharedPreferences("settings", MODE_PRIVATE)
            prefs.edit().putBoolean("isGrid", false).apply()
        }

        private fun showgrid(){
            lwList.visibility = View.GONE
            rwGrid.visibility = View.VISIBLE
            val prefs = getSharedPreferences("settings", MODE_PRIVATE)
            prefs.edit().putBoolean("isGrid", true).apply()
        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_second, menu)
            return true
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort_default -> {
                currentList.clear()
                currentList.addAll(originalList)
                listAdapter.notifyDataSetChanged()
                gridAdapter.notifyDataSetChanged()
                return true
            }
            R.id.sort_asc -> {
                currentList.sortBy{it.price}
                listAdapter.notifyDataSetChanged()
                gridAdapter.notifyDataSetChanged()
                return true
            }
            R.id.sort_desc -> {
                currentList.sortByDescending{it.price}
                listAdapter.notifyDataSetChanged()
                gridAdapter.notifyDataSetChanged()
                return true
            }
        }

        if (item.itemId == R.id.action_cart) {
            startActivity(Intent(this, CartActivity::class.java))
            return true
        }

        if (item.itemId == R.id.action_list){
            showList()
            return true
        }

        if (item.itemId == R.id.action_grid){
            showgrid()
            return true
        }

        if (item.itemId == R.id.action_favorite){
            startActivity(Intent(this, FavoriteActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }



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

        button.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_down))
            }
            if (event.action == MotionEvent.ACTION_UP){
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_up))
            }
            if (event.action == MotionEvent.ACTION_CANCEL){
                v.startAnimation(AnimationUtils.loadAnimation(v.context, R.anim.scale_up))
            }
            false
        }

        button.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("id", product.id)
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