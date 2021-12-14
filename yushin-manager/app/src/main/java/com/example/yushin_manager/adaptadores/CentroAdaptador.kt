package com.example.yushin_manager.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yushin_manager.databinding.CentroBinding
import com.example.yushin_manager.entidades.Centros

class CentroAdaptador(var centro : MutableList<Centros>): RecyclerView.Adapter<CentroAdaptador.CentroContenedor>(){
    override fun getItemCount(): Int {
        return centro.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CentroContenedor {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CentroBinding.inflate(inflater,parent,false)
        return CentroContenedor(binding)
    }

    override fun onBindViewHolder(viewHolder: CentroContenedor, position: Int) {
        viewHolder.bindTarea(centro[position])
    }

    class CentroContenedor(val binding : CentroBinding): RecyclerView.ViewHolder(binding.root){
        fun bindTarea(centro : Centros){
            binding.idDelCentro.text = "Id del centro = ${centro.idCentros.toString()}"
            binding.nombreCentro.text = centro.nombreCentros
            binding.direccionCentro.text = "Dirección : ${centro.direccionCentro}"
        }
    }
}