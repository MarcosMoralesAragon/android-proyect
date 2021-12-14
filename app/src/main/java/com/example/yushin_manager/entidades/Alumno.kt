package com.example.yushin_manager.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Alumno (
    @PrimaryKey(autoGenerate = true)    val idAlumno:Int,
    @ColumnInfo(name = "Nombre")        val nombreAlumno : String,
    @ColumnInfo(name = "Cinturon")      val cinturonAlumno : String,
    @ColumnInfo(name = "Imagen")        val imagenAlumno : String,
    @ColumnInfo(name = "Id centro")     val idCentro : Int,
    @ColumnInfo(name = "Id Profesor")      val idProfesor: Int
    ) : Serializable