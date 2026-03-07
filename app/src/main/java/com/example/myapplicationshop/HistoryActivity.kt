package com.example.myapplicationshop

import CartAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.model.CartStorage
import com.example.myapplicationshop.model.HistoryStorage

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // 1) Находим эл-ы на экране
        val rv = findViewById<RecyclerView>(R.id.rvHistory)

        // 2) Берём товары из истории
        val items = HistoryStorage.all()

        // 3) Настраиваем RecyclerView
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = HistoryAdapter(items)

    }
}