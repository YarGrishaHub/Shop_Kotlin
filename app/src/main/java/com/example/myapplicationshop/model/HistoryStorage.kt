package com.example.myapplicationshop.model

import Product
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HistoryStorage {
    private val items = mutableListOf<Order>()

    private val gson = Gson()

    fun init(context: Context) {
        // подключение к внутреннему хранилищу
        val prefs = context.getSharedPreferences("settings", MODE_PRIVATE)
        // получили json из памяти
        val json = prefs.getString("history_json", null)
        // преобразовали к итоговому типу данных
        if (json != null) {
            val type = object : TypeToken<List<Order>>() {}.type
            val restored : List<Order> = gson.fromJson(json, type)

            HistoryStorage.items.clear() // очистили старую историю
            HistoryStorage.items.addAll(restored) // добавили в историю то, что восстановили из памяти
        }
    }

    // сохранение истории в память
    fun save(context: Context) {
        // подключение к внутреннему хранилищу
        val prefs = context.getSharedPreferences("settings", MODE_PRIVATE)
        // создаём новую json строку
        val json = gson.toJson(items)
        // добавление строки в хранилище
        prefs.edit().putString("history_json", json).apply()
    }

    // функция добавления эл-ов в историю
    // new_item - товар , который мы добавляем
    fun addAll(context: Context, new_list: List<Order>){
        // перебираем все покупки по очереди и добавляем их в список
        for (elem in new_list) {
            items.add(elem)
        }
        save(context)
    }

    // получение списка всех товаров в истории
    fun all() : List<Order> {
        return items.toList()
    }

}