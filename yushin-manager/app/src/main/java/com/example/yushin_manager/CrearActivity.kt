package com.example.yushin_manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.yushin_manager.databinding.ActivityCrearBinding
import com.example.yushin_manager.entidades.Alumno
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CrearActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCrearBinding
    private lateinit var auth : FirebaseAuth
    private var db : AppDataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        var estaEditando = false

        if(intent.extras?.get("alumno") != null){

            var alumno : Alumno = intent.extras?.get("alumno") as Alumno
            db = AppDataBase.getAppDatabase(this)
            var centroActual = db?.centroDao()?.getCentroPorId(alumno.idCentro)
            estaEditando = true
            binding.textoCrearAlumno.text = "Editar alumno"
            binding.cajaInputNombre.hint = "Antiguo nombre : ${alumno.nombreAlumno}"
            binding.cajaInputCinturon.hint = "Antiguo cinturon : ${alumno.cinturonAlumno}"
            binding.cajaInputCentro.hint = "Antiguo centro : ${centroActual?.nombreCentros}"
        }

        binding.botonGuardar.setOnClickListener {
            if (estaEditando){
                editarUsuario(it)
            } else {
                guardarAlumno(it)
            }
        }
        binding.botonVolver.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    fun guardarAlumno(view : View){
        var nombreAlumno : String = binding.nombre.text.toString().trim()
        var cinturon : String = binding.cinturon.text.toString().trim()
        var nombreCentro : String = binding.centro.text.toString().trim()

        if ((nombreAlumno != "") && (cinturon != "") && (nombreCentro != "")){
            guardarAlumnoABaseDeDatos(nombreAlumno, cinturon, nombreCentro)
            setResult(RESULT_OK)
            finish()
        } else {
            Snackbar.make(view, "Rellene los campos porfavor", Snackbar.LENGTH_LONG).show()
        }
    }

    fun guardarAlumnoABaseDeDatos(nombreAlumno : String, cinturon : String, nombreCentro : String){

        db = AppDataBase.getAppDatabase(this)

        var imagenAlumno = "no tiene"
        var centroAlumno = db!!.centroDao().getCentroPorNombre(nombreCentro)
        var profesorLogeado = db!!.profesorDao().getProfesor(auth.currentUser?.email.toString().trim())

        db?.alumnoDao()?.addAlumno(
            Alumno(0, nombreAlumno, cinturon, imagenAlumno, centroAlumno.idCentros, profesorLogeado.idProfesor)
        )
    }

    fun editarUsuario(view: View){
        var nombreAlumno : String = binding.nombre.text.toString().trim()
        var cinturon : String = binding.cinturon.text.toString().trim()
        var nombreCentro : String = binding.centro.text.toString().trim()

        if ((nombreAlumno != "") && (cinturon != "") && (nombreCentro != "")){
            editarUsuarioBaseDeDatos(nombreAlumno, cinturon, nombreCentro)
            setResult(RESULT_OK)
            finish()
        } else {
            Snackbar.make(view, "Rellene los campos porfavor", Snackbar.LENGTH_LONG).show()
        }
    }

    fun editarUsuarioBaseDeDatos(nombreAlumno : String, cinturon : String, nombreCentro : String){
        db = AppDataBase.getAppDatabase(this)

        var imagenAlumno = "no tiene"
        var centroAlumno = db!!.centroDao().getCentroPorNombre(nombreCentro)
        var profesorLogeado = db!!.profesorDao().getProfesor(auth.currentUser?.email.toString().trim())
        var alumno : Alumno= intent.extras?.get("alumno") as Alumno

        db?.alumnoDao()?.actualizarAlumno(
            Alumno(alumno.idAlumno, nombreAlumno, cinturon, imagenAlumno, centroAlumno.idCentros, profesorLogeado.idProfesor)
        )
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }
}