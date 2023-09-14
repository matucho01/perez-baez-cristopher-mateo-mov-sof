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
import com.example.examenmovilesib.dao.MarcaDAO
import com.example.examenmovilesib.formularios.MarcaForm
import com.example.examenmovilesib.modelo.Marca

class MainActivity : AppCompatActivity() {

    private lateinit var adaptador: ArrayAdapter<Marca>
    private var idItemSeleccionado = 0
    private lateinit var listView: ListView

    private lateinit var marcaDao: MarcaDAO

    private val callback=  registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result ->
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DB.marcaDAO = MarcaDAO(this)
        DB.bicicletaDAO = BicicletaDAO(this)

        this.marcaDao = DB.marcaDAO!!

        listView = findViewById<ListView>(R.id.lv_marcas)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            marcaDao.getLista()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrear = findViewById<Button>(R.id.btn_crear_marca)
        botonCrear.setOnClickListener {

            irActividad(MarcaForm::class.java)
        }

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_marcas)
        botonActualizar.setOnClickListener{
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                marcaDao.getLista()
            )
            onRestart()
            listView.adapter = adaptador
            adaptador.notifyDataSetChanged()
        }

        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.mi_editar -> {
                abrirActividadConParametros(MarcaForm::class.java)
                true
            }

            R.id.mi_eliminar -> {
                abrirDialogoEliminar()
                true
            }

            R.id.mi_ver_marcas -> {
                abrirActividadConParametros(Bicicletas::class.java)
                true
            }

            else -> {
                super.onContextItemSelected(item)
            }
        }

    }

    override fun onRestart() {
        super.onRestart()
        adaptador.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }

    private fun abrirDialogoEliminar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Quieres eliminar la marca?")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            val marcaEliminada = marcaDao.getLista()[idItemSeleccionado]
            if(marcaDao.delete(marcaEliminada.getId())) {
                adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    marcaDao.getLista()
                )
                listView.adapter = adaptador
                adaptador.notifyDataSetChanged()
            }
        }
        builder.setNegativeButton("Cancelar", null)

        val dialog = builder.create()
        dialog.show()
    }

    private fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    private fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("id", marcaDao.getLista()[idItemSeleccionado].getId())
        callback.launch(intentExplicito)
    }
}