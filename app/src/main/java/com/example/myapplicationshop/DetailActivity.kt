package com.example.myapplicationshop

import Product
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationshop.model.CartStorage

class DetailActivity: AppCompatActivity() {
    @SuppressLint("WrongViewCast")
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
        val detailImage = findViewById<ImageView>(R.id.detailImage)
        val detailName = findViewById<TextView>(R.id.detailName)
        val detailPrice = findViewById<TextView>(R.id.detailPrice)
        val detailDescription = findViewById<TextView>(R.id.detaildescription)
        val detailBuy = findViewById<Button>(R.id.detailbuy)
        val detailBack = findViewById<Button>(R.id.detailback)

        detailImage.setImageResource(productImageRes)
        detailName.text = productName
        detailPrice.text = "$productPrice $"
        detailDescription.text = productDescription

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

        detailBack.setOnClickListener{
            finish()
        }
    }
}
