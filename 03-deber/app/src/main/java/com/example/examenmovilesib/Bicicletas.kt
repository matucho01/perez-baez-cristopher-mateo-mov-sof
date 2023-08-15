package com.example.examenmovilesib

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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.examenmovilesib.dao.BicicletaDAO
import com.example.examenmovilesib.formularios.BicicletaForm
import com.example.examenmovilesib.modelo.Bicicleta

class Bicicletas : AppCompatActivity() {

    private val bicicletaDao = BicicletaDAO()
    private lateinit var adaptador: ArrayAdapter<Bicicleta>
    private var idItemSeleccionado = 0
    var id: Int? = -1

    val callback = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
            result ->
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marcas)
        id = intent.getIntExtra("id", -1)


        if (id != -1) {
            val listView = findViewById<ListView>(R.id.lv_view_bicicletas)
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                bicicletaDao.getLista(id!!)
            )
            listView.adapter = adaptador

            val botonCrear = findViewById<Button>(R.id.btn_crear_bicicleta)
            botonCrear.setOnClickListener {
                irActividad(BicicletaForm::class.java)
            }

            registerForContextMenu(listView)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_bicicleta, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    private fun abrirDialogoEliminar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Quieres eliminar la bicicleta?")
        builder.setPositiveButton("Eliminar") { dialog, which ->
            val bicicletaEliminada = bicicletaDao.getLista()[idItemSeleccionado]
            if (bicicletaDao.delete(bicicletaEliminada.getId())) {
                adaptador.remove(bicicletaEliminada)
                adaptador.notifyDataSetChanged()
            }
        }
        builder.setNegativeButton("Cancelar", null)

        val dialog = builder.create()
        dialog.show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                abrirActividadConParametros(BicicletaForm::class.java)
                true
            }
            R.id.mi_eliminar -> {
                abrirDialogoEliminar()
                true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        adaptador.clear()
        adaptador.addAll(bicicletaDao.getLista(id!!))
        adaptador.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        adaptador.clear()
        adaptador.addAll(bicicletaDao.getLista(id!!))
        adaptador.notifyDataSetChanged()
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    private fun abrirActividadConParametros(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("id", bicicletaDao.getLista(id!!).get(idItemSeleccionado).getId())
        intentExplicito.putExtra("marcaId", id)
        callback.launch(intentExplicito)
    }

    fun abrirDialogo(cadena: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(cadena)
        builder.setPositiveButton("Aceptar") { dialog, which -> dialog.dismiss() }

        val dialogo = builder.create()
        dialogo.setCancelable(false)
        dialogo.show()
    }
}