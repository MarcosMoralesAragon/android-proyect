package com.example.yushin_manager.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.yushin_manager.entidades.Centros

@Dao
interface CentrosDao {

    @Query("SELECT * FROM Centros WHERE `Id Profesor` = :idProfesor")
    fun getCentros(idProfesor : Int) : MutableList<Centros>

    @Query("SELECT * FROM Centros WHERE `Nombre centro` = :nombreCentro")
    fun getCentroPorNombre(nombreCentro : String) : Centros

    @Query("SELECT * FROM Centros WHERE idCentros = :idCentro")
    fun getCentroPorId(idCentro : Int) : Centros

    @Insert
    fun addCentro(vararg centros: Centros)

    @Delete
    fun deleteCentro(vararg centros: Centros)

}