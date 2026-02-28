package com.example.myapplicationshop.model

import Product
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object CartStorage {
    // все эл-ы корзины(всё, что в неё добавлено)
    private val items = mutableListOf<Product>()
    private val gson = Gson()

    // восстанавливает корзину из памяти телефона
    fun init(context: Context) {
        // подключение к внутреннему хранилищу
        val prefs = context.getSharedPreferences("settings", MODE_PRIVATE)
        // получили json из памяти
        val json = prefs.getString("cart_json", null)
        // преобразовали к итоговому типу данных
        if (json != null) {
            val type = object : TypeToken<List<Product>>() {}.type
            val restored : List<Product> = gson.fromJson(json, type)

            items.clear() // очистили старую корзину
            items.addAll(restored) // добавили в корзину то, что восстановили из памяти
        }
    }

    // сохранение корзины в память
    fun save(context: Context) {
        // подключение к внутреннему хранилищу
        val prefs = context.getSharedPreferences("settings", MODE_PRIVATE)
        // создаём новую json строку
        val json = gson.toJson(items)
        // добавление строки в хранилище
        prefs.edit().putString("cart_json", json).apply()
    }

    // функция добавления в эл-ов в корзину
    // new_item - товар , который мы добавляем
    fun add_item(context: Context, new_item: Product){
       items.add(new_item)
        save(context)
    }


    // удаление товара из корзины
    fun remove(context: Context, old_item: Product) {
        items.removeAll { it.id == old_item.id }
        save(context)
    }

    // получение списка всех товаров в корзине
    fun all() : List<Product> {
        return items.toList()
    }

    // удаление всего
    fun clear(context: Context) {
        items.clear()
        save(context)
    }


}

