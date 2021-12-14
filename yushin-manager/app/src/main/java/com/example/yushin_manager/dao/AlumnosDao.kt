package com.example.yushin_manager.dao

import androidx.room.*
import com.example.yushin_manager.entidades.Alumno

@Dao
interface AlumnosDao {

    @Query("SELECT * FROM Alumno WHERE `Id Profesor` = :idProfesor")
    fun getAlumnos(idProfesor : Int) : MutableList<Alumno>

    @Insert
    fun addAlumno(vararg alumno: Alumno)

    @Update
    fun actualizarAlumno(vararg alumno: Alumno)

    @Delete
    fun deleteAlumno(vararg alumno: Alumno)
}