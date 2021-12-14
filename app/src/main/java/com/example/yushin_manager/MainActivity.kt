package com.example.yushin_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import com.example.yushin_manager.databinding.ActivityMainBinding
import com.example.yushin_manager.entidades.Alumno
import com.example.yushin_manager.entidades.Centros
import com.example.yushin_manager.entidades.Profesores
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private var db:AppDataBase? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth : FirebaseAuth

    private val respuestaCrear = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            Snackbar.make(binding.root, "Alumno añadido a la lista correctamente", Snackbar.LENGTH_LONG).show()
        } else {
            Snackbar.make(binding.root, "Se ha cancelado añadir al alumno", Snackbar.LENGTH_LONG).show()
        }
    }

    private val respuestaCrearCurso = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            Snackbar.make(binding.root, "Centro añadido a la lista correctamente", Snackbar.LENGTH_LONG).show()
        } else {
            Snackbar.make(binding.root, "Se ha cancelado añadir centro", Snackbar.LENGTH_LONG).show()
        }
    }

    private val respuestaListarAlumno = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            Snackbar.make(binding.root, "Alumno borrado correctamente", Snackbar.LENGTH_LONG).show()
        } else {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDataBase.getAppDatabase(this)

        auth = Firebase.auth

        var emailUser = auth.currentUser?.email.toString().trim()

        binding.nombreUsuario.text = db?.profesorDao()?.getProfesor(emailUser)?.nombreUsuario

        binding.botonAddAlumno.setOnClickListener { addAlumno() }
        binding.botonAddCentro.setOnClickListener { addCentro() }

        binding.botonListaNinos.setOnClickListener { irAListaAlumno() }
        binding.botonListaCentro.setOnClickListener { irAListaCentro() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.global_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.cerrarSesionMenu -> {
                auth.signOut()
                var intencion = Intent(this, LoginActivity::class.java)
                startActivity(intencion)
                true
            }
            else ->  super.onOptionsItemSelected(item)
        }
    }

    fun addCentro(){
        val intencion = Intent(this, CrearCentroActivity::class.java)
        respuestaCrearCurso.launch(intencion)
    }

    fun addAlumno(){
        val intencion = Intent(this, CrearActivity::class.java)
        respuestaCrear.launch(intencion)
    }

    fun irAListaAlumno(){
        var intencion = Intent(this, ListaAlumnosActivity::class.java)
        respuestaListarAlumno.launch(intencion)
    }

    fun irAListaCentro(){
        var intencion = Intent(this, ListaCentroActivity::class.java)
        startActivity(intencion)
    }

    fun iniciarBaseDeDatos(){
        db?.alumnoDao()?.addAlumno(
            Alumno(1,"David", "Amarillo", "noTiene", 1, 1)
        )
        db?.profesorDao()?.addProfesor(
            Profesores(1, "Miguel", "prueba2@mail.com")
        )
        db?.centroDao()?.addCentro(
            Centros(1,"Victoria Kent", "no tiene", "no tiene direccion", 1),
        )
    }
}