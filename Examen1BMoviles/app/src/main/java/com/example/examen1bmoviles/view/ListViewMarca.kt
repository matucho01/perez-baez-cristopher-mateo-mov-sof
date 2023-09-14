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
import com.example.examen1bmoviles.R
import com.example.examen1bmoviles.controller.ActualizarMarca
import com.example.examen1bmoviles.controller.CrearMarca
import com.example.examen1bmoviles.model.Marca
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class ListViewMarca : AppCompatActivity() {

    private var nombreMarca: String? = null
    private val db = FirebaseFirestore.getInstance()
    private var adaptadorMarca: ArrayAdapter<Marca>? = null

    private val arregloMarca: ArrayList<Marca> = arrayListOf()
    private var idItemSeleccionado = 0
    private var idMarca = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_marca)

        // Inicializar adaptador
        val listViewMarca = findViewById<ListView>(R.id.lv_list_view_marca)
        adaptadorMarca = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloMarca
        )

        listViewMarca.adapter = adaptadorMarca

        // Obtener y mostrar las marcas desde Firestore
        registerForContextMenu(listViewMarca)
        adaptadorMarca!!.notifyDataSetChanged()

        val botonInternoAniadirMarca =
            findViewById<Button>(R.id.btn_aniadir_externo_marca)
        botonInternoAniadirMarca.setOnClickListener {
            irActividadDesdeMarca(idMarca, CrearMarca::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        consultarMarcas(adaptadorMarca!!)

        adaptadorMarca!!.notifyDataSetChanged()
    }

    private fun consultarMarcas(adaptadorMarca: ArrayAdapter<Marca>) {

        val db = Firebase.firestore
        val marcasRefUnico = db.collection("marcas")
        arregloMarca.clear()
        adaptadorMarca.notifyDataSetChanged()
        marcasRefUnico.orderBy("nombre").get().addOnSuccessListener {
            for (marca in it) {
                marca.id
                aniadirArregloMarca(marca)
            }
            adaptadorMarca.notifyDataSetChanged()
        }
            .addOnFailureListener {

            }
    }

    private fun aniadirArregloMarca(marca: QueryDocumentSnapshot?) {

        val fechaCreacionTexto = marca?.getString("fechaCreacion")

        // Suponiendo que la fecha esté en formato 'dd/MM/yyyy'
        val partesFecha = fechaCreacionTexto?.split("/")
        if (partesFecha != null && partesFecha.size == 3) {
            val fechaFormateada = "${partesFecha[2]}-${partesFecha[1]}-${partesFecha[0]}"
            val marcaNuevo = Marca(
                marca?.id,
                marca?.getString("nombre"),
                marca?.getString("pais"),
                LocalDate.parse(fechaFormateada),
                marca?.getString("sede")
            )
            arregloMarca.add(marcaNuevo)
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
        inflater.inflate(R.menu.menu_context_marca, menu)

        // obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var idMarca = arregloMarca[idItemSeleccionado].id
        nombreMarca = arregloMarca[idItemSeleccionado].nombre

        return when (item.itemId) {
            R.id.menu_editar_marca -> {
                irActividadDesdeMarca(idMarca, ActualizarMarca::class.java)
                return true
            }

            R.id.menu_eliminar_marca -> {
               eliminarMarca(idMarca!!)
                return true
            }

            R.id.menu_bicicletas_marca -> {
                irActividadDesdeMarca(idMarca, ListViewBicicleta::class.java)
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }
    private fun eliminarMarca(
        idMarca : String
    ) {
        val db = Firebase.firestore
        val marcaRef = db.collection("marcas")
        marcaRef
            .document(idMarca)
            .delete()
        consultarMarcas(adaptadorMarca!!)
        mostrarSnackBar("Marca eliminada satisfactoriamente")
    }

    private fun irActividadDesdeMarca(
        idMarca: String?, clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("nombreMarca", nombreMarca)
        intent.putExtra("idItemSeleccionado", idItemSeleccionado)
        intent.putExtra("idMarca", idMarca)

        startActivity(intent)
    }

    private fun mostrarSnackBar(mensaje: String) {
        val rootView = findViewById<View>(android.R.id.content)
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_SHORT).show()
    }
}