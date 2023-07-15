package com.example.examenmovilesib.formularios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.examenmovilesib.Bicicletas
import com.example.examenmovilesib.R
import com.example.examenmovilesib.dao.BicicletaDAO
import com.example.examenmovilesib.dao.MarcaDAO
import com.example.examenmovilesib.modelo.Bicicleta

class BicicletaForm : AppCompatActivity() {
    private val bicicletaDao = BicicletaDAO()
    private val marcaDao = MarcaDAO()
    var id: Int? =null
    private var marcaId: Int? =null

    val callback = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
            result ->
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bicicleta_form)
        id = intent.getIntExtra("id", -1)
        marcaId = intent.getIntExtra("marcaId", -1)

        val modeloInput = findViewById<EditText>(R.id.input_modelo)
        val tipoInput = findViewById<EditText>(R.id.input_tipo)
        val anioInput = findViewById<EditText>(R.id.input_anio)
        val precioInput = findViewById<EditText>(R.id.input_precio)
        val disponibleInput = findViewById<CheckBox>(R.id.cb_disponible)
        val marcaInput = findViewById<EditText>(R.id.input_marca)
        val textoBicicleta = findViewById<TextView>(R.id.tv_bicicleta_actual)
        val btnAceptar = findViewById<Button>(R.id.btn_aceptar_bicicleta)
        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_bicicleta)

        btnCancelar.setOnClickListener {
            onBackPressed()
        }

        if (id != -1) {
            val bicicleta = bicicletaDao.get(id!!)
            if (bicicleta != null) {
                textoBicicleta.text = "Editar Bicicleta"

                modeloInput.setText(bicicleta.getModelo())
                tipoInput.setText(bicicleta.getTipo())
                anioInput.setText(bicicleta.getAnio().toString())
                disponibleInput.isChecked = bicicleta.getDisponible()
                precioInput.setText(bicicleta.getPrecio().toString())
                marcaInput.setText(bicicleta.getMarcaId().toString())

                btnAceptar.setOnClickListener {
                    try {
                        val modelo = modeloInput.text.toString()
                        val tipo = tipoInput.text.toString()
                        val anio = anioInput.text.toString()
                        val precio = precioInput.text.toString()
                        val disponible = disponibleInput.isChecked
                        val marcaId = marcaInput.text.toString()

                        if (modelo.isNotBlank() && tipo.isNotBlank() && anio.isNotBlank()
                            && precio.isNotBlank() && marcaId.isNotBlank() &&
                            marcaDao.existe(marcaId.toInt())
                        ) {
                            bicicleta.setModelo(modelo)
                            bicicleta.setTipo(tipo)
                            bicicleta.setAnio(anio.toInt())
                            bicicleta.setPrecio(precio.toDouble())
                            bicicleta.setDisponible(disponible)
                            bicicleta.setMarcaId(marcaId.toInt())
                            bicicletaDao.edit(bicicleta)
                            abrirDialogoYVolver("Bicicleta actualizada")
                        } else {
                            if (!marcaDao.existe(marcaId.toInt())) {
                                abrirDialogo("La bicicleta no existe")
                            } else {
                                abrirDialogo("Faltan datos por ingresar")
                            }
                        }
                    } catch (e: Exception) {
                        abrirDialogo("Los datos no están en el formato correcto")
                    }
                }
            } else {
                abrirDialogo("La bicicleta no existe")
            }
        } else {
            textoBicicleta.text = "Crear Bicicleta"

            btnAceptar.setOnClickListener {
                try {
                    val modelo = modeloInput.text.toString()
                    val tipo = tipoInput.text.toString()
                    val anio = anioInput.text.toString()
                    val precio = precioInput.text.toString()
                    val disponible = disponibleInput.isChecked
                    val marcaId = marcaInput.text.toString()

                    if (modelo.isNotBlank() && tipo.isNotBlank() && anio.isNotBlank() &&
                        precio.isNotBlank() && marcaId.isNotBlank() &&
                        marcaDao.existe(marcaId.toInt())
                    ) {
                        val bicicleta = Bicicleta(
                            0,
                            modelo,
                            tipo,
                            anio.toInt(),
                            precio.toDouble(),
                            disponible,
                            marcaId.toInt()
                        )

                        bicicletaDao.add(bicicleta)
                        abrirDialogo("Bicicleta registrada")
                        modeloInput.setText("")
                        tipoInput.setText("")
                        anioInput.setText("")
                        disponibleInput.isChecked = false
                        precioInput.setText("")
                        marcaInput.setText("")
                    } else {
                        if (!marcaDao.existe(marcaId.toInt())) {
                            abrirDialogo("La bicicleta no existe")
                        } else {
                            abrirDialogo("Faltan datos por ingresar")
                        }
                    }
                } catch (e: Exception) {
                    abrirDialogo("Los datos no están en el formato correcto")
                }
            }
        }
    }

    private fun abrirDialogoYVolver(cadena: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(cadena)
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            abrirActividadConParametros(Bicicletas::class.java)
            dialog.dismiss()
        }

        val dialogo = builder.create()
        dialogo.setCancelable(false)
        dialogo.show()
    }

    private fun abrirDialogo(cadena: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(cadena)
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }

        val dialogo = builder.create()
        dialogo.setCancelable(false)
        dialogo.show()
    }

    private fun abrirActividadConParametros(clase: Class<*>) {
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("id", marcaId)
        callback.launch(intentExplicito)
    }
}