package com.example.examenmovilesib

import com.example.examenmovilesib.modelo.Bicicleta
import com.example.examenmovilesib.modelo.Marca
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BDDMemoria {
    companion object{
        val listaMarcas = arrayListOf<Marca>()
        val listaBicicletas = arrayListOf<Bicicleta>()

        init {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            listaMarcas.add(Marca(1, "Pinarello", "Italia", LocalDate.parse("09/03/1953", formatter), "Quito"))
            listaMarcas.add(Marca(2, "Giant", "Taiwán", LocalDate.parse("01/01/1972", formatter), "Guayaquil"))
            listaMarcas.add(Marca(3, "Trek", "Estados Unidos", LocalDate.parse("01/01/1976", formatter), "Cuenca"))
            listaMarcas.add(Marca(4, "Specialized", "Estados Unidos", LocalDate.parse("01/01/1974", formatter), "Ambato"))

            listaBicicletas.add(Bicicleta(1, "Dogma F", "ruta", 2023, 8899.99, true, 1))
            listaBicicletas.add(Bicicleta(2, "Trek Emonda", "ruta", 2021, 5799.99, true, 3))
            listaBicicletas.add(Bicicleta(3, "XTC Advanced", "montaña", 2021, 6799.99, true, 2))
            listaBicicletas.add(Bicicleta(4, "Epic", "montaña", 2021, 4999.99, true, 4))
        }
    }
}