package com.example.datastorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var edtFistName:EditText
    private lateinit var edtLastName:EditText
    private lateinit var txtDataFistName:TextView
    private lateinit var txtDataLastName:TextView
    private lateinit var btnSave:Button
    private lateinit var btnRealData:Button
    private lateinit var userManager: UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtFistName=findViewById(R.id.edtFistName)
        edtLastName=findViewById(R.id.edtLastName)
        txtDataFistName=findViewById(R.id.txtDataFistName)
        txtDataLastName=findViewById(R.id.txtDataLastName)
        btnSave=findViewById(R.id.btnSave)
        btnRealData=findViewById(R.id.btnRealData)
        userManager=UserManager(this)

        btnSave.setOnClickListener {
            saveData()
        }
        btnRealData.setOnClickListener {
            realData()
        }
    }
    private fun realData() {
        GlobalScope.launch(Dispatchers.IO) {
            val firstName = userManager.fistNameFlow().first()// Collect the first name immediately
            val lastName = userManager.lastNameFlow().first()   // Collect the last name immediately
            GlobalScope.launch(Dispatchers.Main) {
                txtDataFistName.text = "First Name: $firstName"
                txtDataLastName.text = "Last Name: $lastName"
            }
        }
    }

    private fun saveData() {
        val textFistName=edtFistName.text.toString()
        val textLastName=edtLastName.text.toString()
        GlobalScope.launch(Dispatchers.IO){
            userManager.saveUser(textFistName,textLastName)
        }
        Toast.makeText(this,"Luu du lieu thanh cong",Toast.LENGTH_SHORT).show()
    }
}