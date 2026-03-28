package com.example.myapplicationshop.model

import Product
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object FavoriteStorage {
    private val items = mutableListOf<Product>()
    private val gson = Gson()

    fun init(context: Context) {
        val prefs = context.getSharedPreferences("settings", MODE_PRIVATE)
        val json = prefs.getString("favorite_json", null)
        if (json != null) {
            val type = object : TypeToken<List<Product>>() {}.type
            val restored : List<Product> = gson.fromJson(json, type)

            items.clear()
            items.addAll(restored)
        }
    }


    fun save(context: Context) {
        val prefs = context.getSharedPreferences("settings", MODE_PRIVATE)
        val json = gson.toJson(items)
        prefs.edit().putString("favorite_json", json).apply()
    }

    fun add_item(context: Context, new_item: Product): Boolean{
        val exists = items.any{it.id == new_item.id}

        if (exists == true){
            return false
        } else {
            items.add(new_item)
            save(context)
            return true
        }
        items.add(new_item)
        save(context)
    }

    fun remove(context: Context, old_item: Product) {
        items.removeAll { it.id == old_item.id }
        save(context)
    }

    fun all() : List<Product> {
        return items.toList()
    }

    fun clear(context: Context) {
        items.clear()
        save(context)
    }
}