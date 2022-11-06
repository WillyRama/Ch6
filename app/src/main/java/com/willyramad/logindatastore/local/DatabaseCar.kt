package com.willyramad.logindatastore.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FavoriteCar::class], version = 1)
abstract class DatabaseCar : RoomDatabase() {

    abstract fun carDao() : FavoriteDao
    companion object{
        private var INSTANCE : DatabaseCar? = null

        fun getInstance(context : Context):DatabaseCar? {
            if (INSTANCE == null){
                synchronized(DatabaseCar::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DatabaseCar::class.java,"favoritcar.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}