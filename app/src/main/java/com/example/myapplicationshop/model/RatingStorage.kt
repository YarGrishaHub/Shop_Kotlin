package com.example.myapplicationshop.model

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.media.Rating

object RatingStorage {
    fun save(context: Context, productid: Int, rating: Float) {
        val prefs = context.getSharedPreferences("rating_prefs", MODE_PRIVATE)
        prefs.edit().putFloat(productid.toString(), rating).apply()
    }

    fun get(context: Context, productid: Int): Float {
        val prefs = context.getSharedPreferences("rating.prefs", MODE_PRIVATE)
        return prefs.getFloat(productid.toString(), 0f)
    }
}