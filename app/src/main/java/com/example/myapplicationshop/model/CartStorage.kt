package com.example.myapplicationshop.model

import Product

object CartStorage {
    // все эл-ы корзины(всё, что в неё добавлено)
    private val items = mutableListOf<Product>()
    // функция добавления в эл-ов в корзину
    // new_item - товар , который мы добавляем
    fun add_item(new_item: Product){
        // проверяем есть ли товар в корзине
        val CheckInCart = items.any { it.id == new_item.id }

        // если такого товара нет, то добавляем в корзину
        if (!CheckInCart) {
            items.add(new_item)
        }
    }

    // удаление товара из корзины
    fun remove(old_item: Product) {
        items.removeAll { it.id == old_item.id }
    }

    // получение списка всех товаров в корзине
    fun all() : List<Product> {
        return items.toList()
    }

    // удаление всего
    fun clear() {
        items.clear()
    }


}

