package com.example.yushin_manager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.yushin_manager.databinding.LoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity:AppCompatActivity(){

    private lateinit var binding : LoginBinding
    private lateinit var auth : FirebaseAuth

    private val respuestaRegistro = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            Toast.makeText(this, "Registro correcto", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Registro cancelado", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonInicio.setOnClickListener { logIn(it) }
        binding.botonRegistro.setOnClickListener { registro() }
    }

    fun logIn(view : View){

        var correo : String = binding.correo.text.toString().trim()
        var pass : String = binding.password.text.toString().trim()

        auth = Firebase.auth

        if  ((correo != "") && (pass != "")) {
            auth.signInWithEmailAndPassword(correo, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intencion = Intent(this, MainActivity::class.java)
                    startActivity(intencion)
                } else {
                    Snackbar.make(view ,"Error en al iniciar sesión", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    fun registro(){
        val intencion = Intent(this, RegisterActivity::class.java)
        respuestaRegistro.launch(intencion)
    }
}