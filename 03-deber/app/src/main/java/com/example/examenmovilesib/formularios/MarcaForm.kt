package com.example.examenmovilesib.formularios

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.examenmovilesib.MainActivity
import com.example.examenmovilesib.R
import com.example.examenmovilesib.dao.MarcaDAO
import com.example.examenmovilesib.modelo.Marca
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MarcaForm : AppCompatActivity() {

    private val marcaDao = MarcaDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marca_form)
        val id: Int? = intent.getIntExtra("id",-1)

        val nombreMarcaInput = findViewById<EditText>(R.id.input_nombre_marca)
        val paisMarcaInput = findViewById<EditText>(R.id.input_pais)
        val fechaCreacionMarcaInput = findViewById<EditText>(R.id.input_fecha_creacion)
        val sedeMarcaInput = findViewById<EditText>(R.id.input_sede)
        val textoMarca = findViewById<TextView>(R.id.tv_marca_actual)
        val btnAceptar = findViewById<Button>(R.id.btn_crear)
        val btnCancelar = findViewById<Button>(R.id.btn_cancelar)

        btnCancelar.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

        if (id != -1){
            val marca = marcaDao.get(id!!)
            textoMarca.text = "Editar Marca"

            nombreMarcaInput.setText(marca!!.getNombre())
            paisMarcaInput.setText(marca.getPais())
            fechaCreacionMarcaInput.setText(
                (marca.getFechaCreacion()!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            )
            sedeMarcaInput.setText(marca.getSede()+"")

            btnAceptar.setOnClickListener {
                try {
                    val nombreMarca = nombreMarcaInput.text.toString()
                    val paisMarca = paisMarcaInput.text.toString()
                    val fechaCreacionMarca = fechaCreacionMarcaInput.text.toString()
                    val sedeMarca = sedeMarcaInput.text.toString()

                    if(nombreMarca != "" && paisMarca != "" && fechaCreacionMarca != "" && sedeMarca != ""){
                        marca.setNombre(nombreMarca)
                        marca.setPais(paisMarca)
                        marca.setFechaCreacion(LocalDate.parse(
                            fechaCreacionMarca, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        )
                        marca.setSede(sedeMarca)
                        marcaDao.edit(marca)
                        abrirDialogoYVolver("Marca actualizada")
                    }else{
                        abrirDialogo("Faltan datos por ingresar")
                    }

                } catch (e:Exception){
                    abrirDialogo("Los datos no están en el formato correcto")
                }
            }
        }else{
            textoMarca.text = "Crear Marca"
            btnAceptar.setOnClickListener {

                try {
                    val nombre = nombreMarcaInput.text.toString()
                    val paisMarca = paisMarcaInput.text.toString()
                    val fechaCreacionMarca = fechaCreacionMarcaInput.text.toString()
                    val sedeMarca = sedeMarcaInput.text.toString()

                    if(nombre != "" && paisMarca != "" && fechaCreacionMarca != "" && sedeMarca != ""){
                        val marca = Marca(
                            0,
                            nombre,
                            paisMarca,
                            LocalDate.parse(fechaCreacionMarca, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            sedeMarca
                        )
                        marcaDao.add(marca)
                        abrirDialogo("Marca registrada satisfactoriamente")
                        nombreMarcaInput.setText("")
                        paisMarcaInput.setText("")
                        fechaCreacionMarcaInput.setText("")
                        sedeMarcaInput.setText("")
                    }else{
                        abrirDialogo("Faltan datos por ingresar")
                    }

                }catch (e: Exception){
                    abrirDialogo("Los datos no están en el formato correcto")
                }
            }
        }
    }

    private fun abrirDialogoYVolver(cadena:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(cadena)
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ // Callback
                    dialog, which -> irActividad(MainActivity::class.java)
            }
        )

        val dialogo = builder.create()
        dialogo.setCancelable(false)
        dialogo.show()

    }

    private fun abrirDialogo(cadena:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(cadena)
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ // Callback
                    dialog, which -> dialog.cancel()
            }
        )
        val dialogo = builder.create()
        dialogo.setCancelable(false)
        dialogo.show()

    }

    private fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        // NO RECIBIMOS RESPUESTA
        startActivity(intent)
        // this.startActivity()
    }
}