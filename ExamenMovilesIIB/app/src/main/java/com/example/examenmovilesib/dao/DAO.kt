package com.example.examenmovilesib.dao

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

abstract class DAO<T> (
    context: Context?,
    ) {
    abstract fun delete(id: Int): Boolean
    abstract fun add(t:T):Unit
    abstract fun edit(t:T):Unit
    abstract fun get(id: Int):T?
    abstract fun getLista(): List<T>
}