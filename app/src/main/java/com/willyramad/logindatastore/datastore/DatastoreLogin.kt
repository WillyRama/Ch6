package com.willyramad.logindatastore.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreLogin(private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

        val Nama = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val USERNAME = stringPreferencesKey("username")
        val PASSWORD = stringPreferencesKey("password")
        val KPASSWORD = stringPreferencesKey("konfirmasi")
    }


    //    MENAMBAHKAN DATA KE PREFERENCES
    suspend fun saveData(name: String, email: String, username: String, password: String, konfirmasi : String) {
        context.dataStore.edit {
            it[Nama] = name
            it[EMAIL] = email
            it[USERNAME] = username
            it[PASSWORD] = password
            it[KPASSWORD] = konfirmasi
        }
    }
    val userName: Flow<String> = context.dataStore.data
        .map {
            it[USERNAME]?:""
        }
    val Em: Flow<String> = context.dataStore.data
        .map {
            it[EMAIL]?:""
        }
    val Nam: Flow<String> = context.dataStore.data
        .map {
            it[Nama]?:""
        }
    val Pass: Flow<String> = context.dataStore.data
        .map {
            it[PASSWORD]?:""
        }
    val KPass: Flow<String> = context.dataStore.data
        .map {
            it[KPASSWORD]?:""
        }
    //    UNTUK CLEAR DATA YANG ADA DI DATASTORE PREFERENCES
    suspend fun clearData(){
        context.dataStore.edit {
            it.clear()
        }
    }
}