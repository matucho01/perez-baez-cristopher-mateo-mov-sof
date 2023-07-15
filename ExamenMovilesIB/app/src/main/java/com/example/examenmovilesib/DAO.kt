package com.example.examenmovilesib

abstract class DAO<T> {
    abstract fun delete(id: Int): Boolean
    abstract fun add(t:T):Unit
    abstract fun edit(t:T):Unit
    abstract fun get(id: Int):T?
    abstract fun getLista(): List<T>
}