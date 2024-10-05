package com.example.datastorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(private val context: Context):ManageResource{
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    companion object {
        val FIST_NAME = stringPreferencesKey("FIST_NAME")
        val LAST_NAME = stringPreferencesKey("LAST_NAME")
    }
    override suspend fun saveUser(fistName: String, lastName: String) {
        context.dataStore.edit {
            it[FIST_NAME]=fistName
            it[LAST_NAME]=lastName
        }
    }
    override suspend fun fistNameFlow(): Flow<String> {
        return  context.dataStore.data
            .map { preferences ->
                preferences[FIST_NAME]?:""
            }
    }
    override suspend fun lastNameFlow(): Flow<String> {
        return  context.dataStore.data
            .map { preferences ->
                preferences[LAST_NAME]?:""
            }
    }
}