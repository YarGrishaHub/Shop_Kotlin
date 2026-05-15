package com.example.myapplicationshop

import Product
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.model.CartStorage
import com.example.myapplicationshop.model.FavoriteStorage

class DetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

//      Получение данных из intent
        val productName = intent.getStringExtra("name") ?: "Товар"
        val productPrice = intent.getDoubleExtra("price", 0.0)
        val productImageRes = intent.getIntExtra("ImageRes",0)
        val productDescription = intent.getStringExtra("description") ?: "Описание отсутствует"
        val productId = intent.getIntExtra("id", -1) // -1 - не пришло

//      Находим View на экране
        val rvImages = findViewById<RecyclerView>(R.id.rvDetailImage)
        val detailName = findViewById<TextView>(R.id.detailName)
        val detailPrice = findViewById<TextView>(R.id.detailPrice)
        val detailDescription = findViewById<TextView>(R.id.detaildescription)
        val detailBuy = findViewById<Button>(R.id.detailbuy)
        val detailBack = findViewById<Button>(R.id.detailback)
        val detailFavorite = findViewById<Button>(R.id.detailfavorite)

        rvImages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val imagesList = listOf(productImageRes, productImageRes, productImageRes)
        rvImages.adapter = ImageGalleryAdapter(imagesList)
        detailName.text = productName
        detailPrice.text = "$productPrice $"
        detailDescription.text = productDescription

        detailName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_up))
        detailPrice.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_up))
        detailDescription.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_up))
        detailBuy.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_up))
        detailBack.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_up))
        detailFavorite.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_up))

        detailBuy.setOnTouchListener { v, event ->
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


        detailBuy.setOnClickListener{
            // 1) Собираем объект класса Product из отдельных переменных
            // Это товар, который мы добавляем
            val one_new_product = Product(
                id = productId,
                name = productName,
                price = productPrice,
                description = productDescription,
                ImageRes = productImageRes)

            // 2) Добавление нового товара в корзину(своя функция)
            CartStorage.add_item(this,one_new_product)

            // 3) Сообщение об успешном добавлении
            Toast.makeText(this, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
        }

        detailBack.setOnTouchListener { v, event ->
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

        detailBack.setOnClickListener{
            finish()
        }

        detailFavorite.setOnClickListener{
            val one_new_product = Product(
                id = productId,
                name = productName,
                price = productPrice,
                description = productDescription,
                ImageRes = productImageRes)

            val added = FavoriteStorage.add_item(this, one_new_product)

            if  (added == true){
                Toast.makeText(this, "Товар добавлен в избранное", Toast.LENGTH_SHORT).show()
            }
            if  (added == false){
                Toast.makeText(this, "Товар уже в избранном", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
