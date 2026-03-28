package com.example.myapplicationshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.model.FavoriteStorage
import com.example.myapplicationshop.model.HistoryStorage

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        // 1) Находим эл-ы на экране
        val rv = findViewById<RecyclerView>(R.id.rvFavorite)

        // 2) Берём список товаров
        val items = FavoriteStorage.all().toMutableList()

        // 3) Настраиваем RecyclerView
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = FavoriteAdapter(items)

    }
}