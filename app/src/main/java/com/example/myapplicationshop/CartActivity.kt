package com.example.myapplicationshop

import CartAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.model.CartStorage

class CartActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // 1) Находим эл-ы на экране
        val rv = findViewById<RecyclerView>(R.id.rvCartList)
        val tvTotal = findViewById<TextView>(R.id.tvCartTotalSum)
        val btnClear = findViewById<Button>(R.id.btnClearCart)

        // 2) Берём товары из CartStorage
        val items = CartStorage.all()

        // 3) Настраиваем RecyclerView
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = CartAdapter(items)

        // 4) Считаем сумму
        var total = 0.0
        for (elem in items) {
            total += elem.price
        }
        tvTotal.text = "Итого: ${total} $"

        // 5) Очистка корзины
        btnClear.setOnClickListener {
            CartStorage.clear(this)
            rv.adapter = CartAdapter(emptyList())
            tvTotal.text = "Итого: 0.0 ₽"
        }

    }
}