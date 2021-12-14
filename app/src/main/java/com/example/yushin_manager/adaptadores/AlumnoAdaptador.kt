package com.example.yushin_manager.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yushin_manager.databinding.AlumnoBinding
import com.example.yushin_manager.entidades.Alumno

class AlumnoAdaptador(var alumno : MutableList<Alumno>,
                      val gestionarPulsacionBorrado: (Alumno) -> Unit,
                      val gestionarPulsacionEdit : (Alumno) -> Unit):RecyclerView.Adapter<AlumnoAdaptador.AlumnoContenedor>() {

    override fun getItemCount(): Int {
        return alumno.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoContenedor {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AlumnoBinding.inflate(inflater,parent,false)
        return AlumnoContenedor(binding)
    }

    override fun onBindViewHolder(viewHolder: AlumnoContenedor, position: Int) {
        viewHolder.bindTarea(alumno[position])
    }

    inner class AlumnoContenedor(val binding : AlumnoBinding):RecyclerView.ViewHolder(binding.root){

        fun bindTarea(alumno : Alumno){

            binding.nombreKarateka.text = alumno.nombreAlumno
            binding.cinturonKarateca.text = "Cinturon ${alumno.cinturonAlumno}"
            binding.centroKarateca.text = "Id centro = ${alumno.idCentro}"

            binding.botonBorrar.setOnClickListener{ gestionarPulsacionBorrado(alumno) }
            binding.botonEditar.setOnClickListener{ gestionarPulsacionEdit (alumno) }
        }
    }
}