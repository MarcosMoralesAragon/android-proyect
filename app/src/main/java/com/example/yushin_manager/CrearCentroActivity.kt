package com.example.yushin_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.yushin_manager.databinding.ActivityCrearBinding
import com.example.yushin_manager.databinding.ActivityCrearCentroBinding
import com.example.yushin_manager.entidades.Centros
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class CrearCentroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearCentroBinding
    private lateinit var auth : FirebaseAuth
    private var db : AppDataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearCentroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonGuardar.setOnClickListener { guardarCentro(it) }
        binding.botonVolver.setOnClickListener  {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    fun guardarCentro(view : View){

        var nombreCentro : String = binding.nombre.text.toString().trim()
        var direccion : String = binding.direccion.text.toString().trim()

        if((nombreCentro != "") && (direccion != "")){
            guardarCentroABaseDeDatos(nombreCentro, direccion)
            setResult(RESULT_OK)
            finish()
        } else {
            Snackbar.make(view, "Rellene los campos para crear el centro", Snackbar.LENGTH_LONG).show()
        }
    }

    fun guardarCentroABaseDeDatos(nombreCentro : String, direccion : String){

        db = AppDataBase.getAppDatabase(this)

        var correoUsuario = auth.currentUser?.email
        var profesor = db!!.profesorDao().getProfesor(correoUsuario!!)

        db?.centroDao()?.addCentro(
            Centros(0, nombreCentro, "", direccion, profesor.idProfesor)
        )

    }
}