package com.example.yushin_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yushin_manager.adaptadores.AlumnoAdaptador
import com.example.yushin_manager.databinding.ActivityListaAlumnosBinding
import com.example.yushin_manager.entidades.Alumno
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ListaAlumnosActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding : ActivityListaAlumnosBinding
    private var db : AppDataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaAlumnosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        db = AppDataBase.getAppDatabase(this)

        var profesor = db!!.profesorDao().getProfesor(auth.currentUser!!.email.toString())
        var listaALumnnos = db!!.alumnoDao().getAlumnos(profesor.idProfesor)

        val pulsacionBorrado : (Alumno) -> Unit = {
            db!!.alumnoDao().deleteAlumno(it)
            setResult(RESULT_OK)
            finish()
        }
        val pulsacionEdit :(Alumno) -> Unit = {
            var intencion = Intent(this, CrearActivity::class.java)
            val datosUsuario = Bundle()
            datosUsuario.putSerializable("alumno", it)
            intencion.putExtras(datosUsuario)
            startActivity(intencion)
            finish()
        }

        val adaptador = AlumnoAdaptador(listaALumnnos,pulsacionBorrado, pulsacionEdit)

        binding.recyclerKarateca.adapter = adaptador
        binding.recyclerKarateca.setHasFixedSize(true)

        binding.botonVolverListaAlumno.setOnClickListener {
            finish()
        }
    }
}