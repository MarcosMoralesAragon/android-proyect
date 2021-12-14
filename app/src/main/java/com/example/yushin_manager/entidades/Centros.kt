package com.example.yushin_manager.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Centros (
    @PrimaryKey(autoGenerate = true)            val idCentros: Int,
    @ColumnInfo(name = "Nombre centro")         val nombreCentros : String,
    @ColumnInfo(name = "Imagen")                val imagenCentro : String?,
    @ColumnInfo(name = "Dirección")             val direccionCentro : String,
    @ColumnInfo(name = "Id Profesor")           val idProfesor : Int
        )