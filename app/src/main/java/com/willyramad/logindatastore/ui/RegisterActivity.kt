package com.willyramad.logindatastore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.willyramad.logindatastore.datastore.DatastoreLogin
import com.willyramad.logindatastore.databinding.ActivityRegisterBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    lateinit var dataLogin: DatastoreLogin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataLogin = DatastoreLogin(this)

        binding.btnregis.setOnClickListener {
            val edNama = binding.nama.text.toString()
            val edEmail = binding.email.text.toString()
            val edUser = binding.user.text.toString()
            val edPass = binding.pass.text.toString()
            val edKpass = binding.pass.text.toString()
            GlobalScope.launch {
                dataLogin.saveData(edNama, edEmail, edUser, edPass, edKpass)
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                finish()
            }
            finish()
        }
    }
}