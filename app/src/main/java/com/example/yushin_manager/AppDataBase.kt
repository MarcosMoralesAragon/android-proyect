package com.example.yushin_manager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yushin_manager.dao.AlumnosDao
import com.example.yushin_manager.dao.CentrosDao
import com.example.yushin_manager.dao.ProfesoresDao
import com.example.yushin_manager.entidades.Alumno
import com.example.yushin_manager.entidades.Centros
import com.example.yushin_manager.entidades.Profesores

@Database(entities = arrayOf(Profesores::class, Alumno::class, Centros::class), version = 1)

abstract class AppDataBase:RoomDatabase() {

    abstract fun alumnoDao(): AlumnosDao
    abstract fun centroDao(): CentrosDao
    abstract fun profesorDao(): ProfesoresDao

    companion object{

        private var instancia: AppDataBase? = null

        @Synchronized
        fun getAppDatabase(context: Context):AppDataBase{
            return instancia?: synchronized(this){
                Room.databaseBuilder(context,AppDataBase::class.java, "yushin-manager-bd")
                    .allowMainThreadQueries()
                    .build()
                    .also { instancia = it }
            }
        }
    }

}