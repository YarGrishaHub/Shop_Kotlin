package com.example.myapplicationshop.model

import Product

data class Order(
    val product: Product,
    val dateTime: String,
    // количество
    val quantity: Int,
    val totalPrice: Double
)
