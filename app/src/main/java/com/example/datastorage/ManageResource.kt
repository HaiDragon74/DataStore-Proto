package com.example.datastorage

import kotlinx.coroutines.flow.Flow

interface ManageResource {
    suspend fun saveUser(fistName:String,lastName:String)
    suspend fun fistNameFlow(): Flow<String>
    suspend fun lastNameFlow(): Flow<String>
}