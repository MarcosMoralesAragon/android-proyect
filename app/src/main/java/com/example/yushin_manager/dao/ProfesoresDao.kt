package com.example.yushin_manager.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.yushin_manager.entidades.Profesores

@Dao
interface ProfesoresDao{

    @Query("SELECT * FROM Profesores WHERE Correo = :correoAuth")
    fun getProfesor(correoAuth : String) : Profesores

    @Insert
    fun addProfesor(vararg profesores: Profesores)
}