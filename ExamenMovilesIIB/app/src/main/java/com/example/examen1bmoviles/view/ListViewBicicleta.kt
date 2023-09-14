package com.example.examen1bmoviles.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.examen1bmoviles.controller.ActualizarBicicleta
import com.example.examen1bmoviles.controller.CrearBicicleta
import com.example.examen1bmoviles.model.Bicicleta
import com.example.examenmovilesib.R
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListViewBicicleta : AppCompatActivity() {

    private var idItemSeleccionadoBicicleta = 0
    private var arregloBicicleta : ArrayList<Bicicleta> = arrayListOf()
    private var adaptadorBicicleta : ArrayAdapter<Bicicleta>? = null

    private var idMarca : String? = ""
    private var idBicicleta : String? = ""
    private var nombreMarca : String? = ""
    private var nombreBicicleta : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_bicicletas)

        // Se recuperan las variables enviadas en la anterior vista
        idMarca = intent.getStringExtra("idMarca")
        nombreMarca = intent.getStringExtra("nombreMarca")

        // Inicializar adaptador
        val listViewBicicleta = findViewById<ListView>(R.id.lv_list_view_bicicleta)
        adaptadorBicicleta = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloBicicleta!!
        )

        listViewBicicleta.adapter = adaptadorBicicleta

        findViewById<TextView>(R.id.txt_nombre_marca_bicicleta).text = nombreMarca

        val botonAniadirBicicleta = findViewById<Button>(
            R.id.btn_aniadir_bicicleta
        )
        botonAniadirBicicleta.setOnClickListener {
            irAgregarBicicleta(CrearBicicleta::class.java)
            adaptadorBicicleta!!.notifyDataSetChanged()
        }

        consultarBicicletas(adaptadorBicicleta!!, idMarca!!)
        adaptadorBicicleta!!.notifyDataSetChanged()

        registerForContextMenu(listViewBicicleta)
    }

    override fun onResume() {
        super.onResume()

        consultarBicicletas(adaptadorBicicleta!!, idMarca!!)
        adaptadorBicicleta!!.notifyDataSetChanged()
            }

    private fun consultarBicicletas(adaptadorBicicleta: ArrayAdapter<Bicicleta>, idMarca: String) {
        val db = Firebase.firestore
        val bicicletasRefUnico = db.collection("marcas").document(idMarca).collection("bicicletas")
        arregloBicicleta.clear()
        adaptadorBicicleta.notifyDataSetChanged()
        bicicletasRefUnico.orderBy("modelo")
            .get()
            .addOnSuccessListener { // it -> eso (lo que llegue)
                for (bicicleta in it){
                    bicicleta.id
                    anadirAArregloBicicleta(bicicleta)
                }
                adaptadorBicicleta.notifyDataSetChanged()
            }
            .addOnFailureListener {
            }
    }

    private fun anadirAArregloBicicleta(bicicleta: QueryDocumentSnapshot?) {

        val bicicletaId = bicicleta?.id
        val existe = arregloBicicleta.any { it.id == bicicletaId }

        if (!existe) {
            val modelo = bicicleta?.getString("modelo")
            val tipo = bicicleta?.getString("tipo")
            val anio = bicicleta?.getLong("anio")?.toInt()
            val precio = bicicleta?.getDouble("precio")
            val disponible = bicicleta?.getBoolean("disponible")

            val nuevoBicicleta = Bicicleta(
                id = bicicletaId,
                modelo = modelo ?: "",
                tipo = tipo ?: "",
                anio = anio ?: 0,
                precio = precio ?: 0.0,
                disponible = disponible ?: false,
                marcaId = null
            )
            arregloBicicleta.add(nuevoBicicleta)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_context_bicicleta, menu)

        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionadoBicicleta = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        idBicicleta = arregloBicicleta[idItemSeleccionadoBicicleta].id
        nombreBicicleta = arregloBicicleta[idItemSeleccionadoBicicleta].modelo
        return when (item.itemId) {
            R.id.menu_editar_bicicleta -> {
                irActividadDesdeBicicleta(ActualizarBicicleta::class.java)
                return true

            }
            R.id.menu_eliminar_bicicleta -> {
               eliminarBicicleta(idMarca, idBicicleta)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun eliminarBicicleta(idMarca: String?, idBicicleta: String?) {
        val db = Firebase.firestore
        val bicicletaReferencia =
            db.collection("marcas").document(idMarca!!).
        collection("bicicletas").document(idBicicleta!!)

        bicicletaReferencia.delete()
        consultarBicicletas(adaptadorBicicleta!!, idMarca)
    }

    private fun irActividadDesdeBicicleta(
            clase: Class<*>
        ) {
            val intent = Intent(this, clase)
            intent.putExtra("idBicicleta", idBicicleta)
            intent.putExtra("idMarca", idMarca)
            intent.putExtra("nombreBicicleta", nombreBicicleta)
            startActivity(intent)
    }

    private fun irAgregarBicicleta(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("nombreMarca",  nombreMarca)
        intent.putExtra("idMarca", idMarca)
        startActivity(intent)
    }
}