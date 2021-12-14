package com.example.yushin_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.yushin_manager.databinding.RegisterBinding
import com.example.yushin_manager.entidades.Profesores
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : RegisterBinding
    private lateinit var auth: FirebaseAuth
    private var db : AppDataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDataBase.getAppDatabase(this)

        binding.botonRegistro.setOnClickListener { registro(it) }
        binding.botonCancelar.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    fun registro(view:View){
        var correo : String = binding.correo.text.toString().trim()
        var pass : String = binding.passwordRegister.text.toString().trim()

        auth = Firebase.auth
        if  ((correo != "") && (pass != "")){
            auth.createUserWithEmailAndPassword(correo,pass).addOnCompleteListener(this){
                if (it.isSuccessful){
                    setResult(RESULT_OK)
                    guardarALaBaseDeDatos()
                    finish()
                } else  {
                    Snackbar.make(binding.root, "Error en los campos", Snackbar.LENGTH_LONG).show()
                }
            }
        } else {
            Snackbar.make(binding.root, "Revisa los campos vacios", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }
    fun guardarALaBaseDeDatos(){

        var usuario = binding.usuario.text.toString().trim()
        var correo = binding.correo.text.toString().trim()

        db?.profesorDao()?.addProfesor(
            Profesores(0, usuario , correo)
        )
    }
}