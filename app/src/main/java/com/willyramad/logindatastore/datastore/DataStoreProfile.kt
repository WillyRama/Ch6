package com.willyramad.logindatastore.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreProfile (private val context: Context){
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Profile")

        val Nama = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val UMUR = stringPreferencesKey("umur")
    }


    //    MENAMBAHKAN DATA KE PREFERENCES
    suspend fun saveData(name: String, email: String, umur: String) {
        context.dataStore.edit {
            it[Nama] = name
            it[EMAIL] = email
            it[UMUR] = umur
        }
    }
    val Um: Flow<String> = context.dataStore.data
        .map {
            it[UMUR]?:""
        }
    val Em: Flow<String> = context.dataStore.data
        .map {
            it[EMAIL]?:""
        }
    val Nam: Flow<String> = context.dataStore.data
        .map {
            it[Nama]?:""
        }
    //    UNTUK CLEAR DATA YANG ADA DI DATASTORE PREFERENCES
    suspend fun clearData(){
        context.dataStore.edit {
            it.clear()
        }
    }
}