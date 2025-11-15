package com.example.myapplicationshop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

//      Получение данных из intent
        val productName = intent.getStringExtra("name") ?: "Товар"
        val productPrice = intent.getDoubleExtra("price", 0.0)
        val productImageRes = intent.getIntExtra("ImageRes",1)
        val productDescription = intent.getStringExtra("description") ?: "Описание отсутствует"

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
    }
}
