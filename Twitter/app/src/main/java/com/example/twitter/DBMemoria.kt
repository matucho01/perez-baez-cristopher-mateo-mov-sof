package com.example.twitter

class DBMemoria {
    companion object {
        val arregloTweets = arrayListOf<Tweet>(
            Tweet("¡Feliz viernes! \uD83D\uDE0A Disfrutando de un buen café mientras trabajo en mis proyectos. ☕ #ViernesVibes #Productividad", 75, 8, "Mateo Pérez", "@matucho01", R.drawable.mateoperez, "1h", 3, 457),
            Tweet("Momentos especiales con la familia son tesoros invaluables. ❤️ #Familia #AmorInfinito #MomentosFelices", 167, 50, "Nhazir Faz", "@nhazirfazf", R.drawable.nhaza, "3h", 3, 2434),
            Tweet("Amor por los libros \uD83D\uDCDA\uD83D\uDC95 Disfrutando de una tarde de lectura con una buena novela. #Lectura #AmoLosLibros", 51, 22, "Mateo Díaz", "@mateodiaz2001", R.drawable.mateodiaz, "4h", 3, 1453),
            Tweet("La mejor manera de predecir el futuro es creándolo. \uD83D\uDCAA #Motivación #Emprendimiento #FuturoBrillante", 1, 35, "Bryan Tapia", "brynta79", R.drawable.bryantapia, "6h", 3, 5264),
            Tweet("Las noches de verano son mágicas ✨ ¡Disfrutando de una cena al aire libre con amigos! \uD83C\uDF7D️ #NocheDeVerano #Amigos", 1, 26, "Fernando Soto", "ferphotos23", R.drawable.ferfotos, "8h", 42, 41),
            Tweet("Hola mundo de Twitter que ya no es Twitter", 178, 254, "Emilio Montalvo", "emiliomontalvo12", R.drawable.emiliomontalvo, "10h", 13, 354),
            Tweet("Pensando en por qué tuvieron que pasar las cosas de esta forma", 531, 678, "Andrea Pineda", "@__andreapineda__", R.drawable.andreapineda_, "1h", 34, 2534),
        )

        val arregloMensajes = arrayListOf<Message>(
            Message("Hola, el profe dice que confirmes para lo de la tesis.", "Emilio", "@emiliomontalvo12", R.drawable.emiliomontalvo, "1h"),
            Message("Ya se me acaban las vacaciones y usted no asoma", "Andrea", "@__andreapineda__", R.drawable.andreapineda_, "2h"),
            Message("Which email should I contact to?", "Nhazir Faz", "@nhazirfazf", R.drawable.nhaza, "4h"),
            Message("Oppenheimer es la mejor película del mundo ñaño", "Mateo Díaz", "@mateodiaz2001", R.drawable.mateodiaz, "12h"),
            Message("Confirme", "Fernando Soto", "@ferphotos23", R.drawable.ferfotos, "24h"),
            Message("Hola, te invito a seguirme en mi cuenta oficial @matucho01", "Bryan Tapia", "@brynta79", R.drawable.bryantapia, "7 Jul 23"),
            Message("Estuvo buena la carrera?", "Lorena Baez", "@silvilore.baez", R.drawable.lorenabaez, "6 Jul 23"),
            Message("Holaaa perdida", "Daniela", "@danielacruz12", R.drawable.danielacruz, "5 Jul 23"),
            Message("Holaaa perdida", "Majo Rubio", "@majito.rubio", R.drawable.majorubio, "4 Jul 23"),
            Message("Confirme founder", "Alex Padilla", "@padimaster", R.drawable.founder, "3 Jul 23"),
        )
    }
}