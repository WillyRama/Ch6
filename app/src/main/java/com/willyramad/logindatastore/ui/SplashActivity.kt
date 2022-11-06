package com.willyramad.logindatastore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.asLiveData
import com.willyramad.logindatastore.datastore.DatastoreLogin
import com.willyramad.logindatastore.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding : ActivitySplashBinding
    lateinit var dataLogin: DatastoreLogin
    lateinit var usernama: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataLogin = DatastoreLogin(this)
        dataLogin.userName.asLiveData().observe(this,{
            usernama = it.toString()
        })

        Handler().postDelayed({
            if (usernama.isNullOrEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }


        },3000)
    }
}