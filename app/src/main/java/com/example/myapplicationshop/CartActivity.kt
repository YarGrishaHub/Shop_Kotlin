package com.example.myapplicationshop

import CartAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationshop.model.CartStorage
import com.example.myapplicationshop.model.HistoryStorage
import com.example.myapplicationshop.model.Order
import java.util.Date
import java.util.Locale.getDefault

class CartActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // 1) Находим эл-ы на экране
        val rv = findViewById<RecyclerView>(R.id.rvCartList)
        val tvTotal = findViewById<TextView>(R.id.tvCartTotalSum)
        val btnClear = findViewById<Button>(R.id.btnClearCart)
        val btnMakeOrder = findViewById<Button>(R.id.btnMakeOrder)
        val btnOpenHistory = findViewById<Button>(R.id.btnOpenHistory)

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

        // 6) Переход в Историю
        btnOpenHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

            // 7) Оформление заказа
        btnMakeOrder.setOnClickListener {
            val cartItems = CartStorage.all() // получили все товары в корзине
            if (cartItems.isEmpty()) { // если корзина пустая, то ничего не делаем
             return@setOnClickListener
            }

            val formatter = java.text.SimpleDateFormat("dd.MM.yyyy HH:mm", getDefault())
            val dateTime = formatter.format(Date())

            var purchases = mutableListOf<Order>()

            for (elem in cartItems) {

                var found = false // следит нашёлся, товар или нет

                // проверка всех товаров в истории
                for (i in purchases.indices) {
                    if (elem.id == purchases[i].product.id) {
                        //увеличиваем кол-во
                        var old = purchases[i]
                        purchases[i] = Order(
                            old.product,
                            old.dateTime,
                            old.quantity + 1,
                            old.totalPrice + old.product.price)
                        found = true
                        break
                    }
                }

                // если товара ещё нет в истории
                if (found == false) {
                    purchases.add(Order(elem, dateTime, 1, elem.price * 1))
                }

            }

            // сохранение покупок в истории
            HistoryStorage.addAll(this, purchases)

            // очистка корзины
            CartStorage.clear(this)

            // обновляем экран
            rv.adapter = CartAdapter(emptyList())
            tvTotal.text = "ИТОГО: 0.0 $"

        }

    }
}