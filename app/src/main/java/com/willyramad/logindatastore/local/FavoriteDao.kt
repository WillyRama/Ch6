package com.willyramad.logindatastore.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavoriteDao {
    @Insert
    fun addToFav(favoriteCar: FavoriteCar):Long

    @Query("SELECT * FROM FavoriteCar")
    fun getFavCar(): List<FavoriteCar>

    @Query("SELECT count(*) FROM FavoriteCar WHERE FavoriteCar.id = :id")
    fun chackCar(id :Int) : Int
}