package com.example.examenmovilesib

import com.example.examenmovilesib.dao.BicicletaDAO
import com.example.examenmovilesib.dao.MarcaDAO

class DB {
    companion object{
        var marcaDAO: MarcaDAO? = null
        var bicicletaDAO: BicicletaDAO? = null
    }
}