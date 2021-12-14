package com.example.yushin_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yushin_manager.adaptadores.AlumnoAdaptador
import com.example.yushin_manager.adaptadores.CentroAdaptador
import com.example.yushin_manager.databinding.ActivityListaAlumnosBinding
import com.example.yushin_manager.databinding.ActivityListaCentroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ListaCentroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListaCentroBinding
    private lateinit var auth : FirebaseAuth
    private var db : AppDataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaCentroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        db = AppDataBase.getAppDatabase(this)

        var profesor = db!!.profesorDao().getProfesor(auth.currentUser!!.email.toString())
        var listaCentros = db!!.centroDao().getCentros(profesor.idProfesor)

        val adaptador = CentroAdaptador(listaCentros)
        binding.recyclerCentro.adapter = adaptador
        binding.recyclerCentro.setHasFixedSize(true)

        binding.botonVolverListaCentro.setOnClickListener {
            var intencion = Intent(this, MainActivity::class.java)
            startActivity(intencion)
        }
    }
}