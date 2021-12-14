package com.example.yushin_manager.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profesores(
    @PrimaryKey(autoGenerate = true)    val idProfesor : Int,
    @ColumnInfo(name = "Nombre")        val nombreUsuario:String,
    @ColumnInfo(name = "Correo")        val correoUsuario:String,
)