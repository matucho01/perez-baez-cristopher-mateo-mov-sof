package com.example.twitter

class DBMemoria {
    companion object {
        val arregloTweets = arrayListOf<Tweet>(
            Tweet("¡Feliz viernes! \uD83D\uDE0A Disfrutando de un buen café mientras trabajo en mis proyectos. ☕ #ViernesVibes #Productividad", 75, 8, "Mateo Pérez", "@matucho01", R.drawable.mateoperez, "1h", 3, 457),
            Tweet("Momentos especiales con la familia son tesoros invaluables. ❤️ #Familia #AmorInfinito #MomentosFelices", 167, 50, "Nhazir Faz", "@nhazirfazf", R.drawable.nhaza, "1h", 3, 2434),
            Tweet("Amor por los libros \uD83D\uDCDA\uD83D\uDC95 Disfrutando de una tarde de lectura con una buena novela. #Lectura #AmoLosLibros", 51, 22, "Mateo Díaz", "@mateodiaz2001", R.drawable.mateodiaz, "1h", 3, 4),
            Tweet("La mejor manera de predecir el futuro es creándolo. \uD83D\uDCAA #Motivación #Emprendimiento #FuturoBrillante", 1, 35, "Bryan Tapia", "brynta79", R.drawable.bryantapia, "1h", 3, 4),
            Tweet("Las noches de verano son mágicas ✨ ¡Disfrutando de una cena al aire libre con amigos! \uD83C\uDF7D️ #NocheDeVerano #Amigos", 1, 26, "Fernando Soto", "ferphotos23", R.drawable.ferfotos, "1h", 3, 4),
            Tweet("Hola", 1, 254, "Emilio Montalvo", "emiliomontalvo12", R.drawable.emiliomontalvo, "1h", 3, 4),
            Tweet("Hola", 1, 78, "Andrea Pineda", "@__andreapineda__", R.drawable.andreapineda_, "1h", 3, 4),
        )

        val arregloMensajes = arrayListOf<Message>(
            Message("Hola", "Emilio Montalvo", "@emiliomontalvo12", R.drawable.emiliomontalvo, "21 Jul 23"),
            Message("Hola", "Andrea Pineda", "@__andreapineda__", R.drawable.andreapineda_, "21 Jul 23"),
            Message("Hola", "Mateo Pérez", "@matucho01", R.drawable.mateoperez, "21 Jul 23"),
            Message("Hola", "Nhazir Faz", "@nhazirfazf", R.drawable.nhaza, "21 Jul 23"),
            Message("Hola", "Mateo Díaz", "@mateodiaz2001", R.drawable.mateodiaz, "21 Jul 23"),
            Message("Hola", "Bryan Tapia", "@brynta79", R.drawable.bryantapia, "21 Jul 23"),
            Message("Hola", "Fernando Soto", "@ferphotos23", R.drawable.ferfotos, "21 Jul 23"),
            Message("Hola", "Emilio Montalvo", "@emiliomontalvo12", R.drawable.emiliomontalvo, "21 Jul 23"),
            Message("Hola", "Andrea Pineda", "@__andreapineda__", R.drawable.andreapineda_, "21 Jul 23"),
            Message("Hola", "Mateo Pérez", "@matucho01", R.drawable.mateoperez, "21 Jul 23"),
            Message("Hola", "Nhazir Faz", "@nhazirfazf", R.drawable.nhaza, "21 Jul 23"),
            Message("Hola", "Mateo Díaz", "@mateodiaz2001", R.drawable.mateodiaz, "21 Jul 23"),
            Message("Hola", "Bryan Tapia", "@brynta79", R.drawable.bryantapia, "21 Jul 23"),
            Message("Hola", "Fernando Soto", "@ferphotos23", R.drawable.ferfotos, "21 Jul 23"),
        )
    }
}