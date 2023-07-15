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
import com.example.examenmovilesib.dao.MarcaDAO
import com.example.examenmovilesib.modelo.Marca

class MainActivity : AppCompatActivity() {

    val marcaDao = MarcaDAO()
    lateinit var adaptador: ArrayAdapter<Marca>
    var idItemSeleccionado = 0
    val callback=  registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result ->
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.lv_marcas)
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
                abrirActividadConParametros(Marcas::class.java)
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

    fun abrirDialogoEliminar() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Esta seguro que desea eliminar el autor?")
        builder.setPositiveButton("Si") { dialog, which ->
            if(marcaDao.delete(marcaDao.getLista().get(idItemSeleccionado).getId())){
                adaptador.notifyDataSetChanged()
            }
        }
        builder.setNegativeButton("No", null)

        val dialog = builder.create()
        dialog.show()
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("id", marcaDao.getLista().get(idItemSeleccionado).getId())
        callback.launch(intentExplicito)
    }
}