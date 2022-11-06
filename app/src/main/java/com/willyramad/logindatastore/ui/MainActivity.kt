package com.willyramad.logindatastore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.willyramad.logindatastore.datastore.DatastoreLogin
import com.willyramad.logindatastore.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var dataLogin : DatastoreLogin
    lateinit var usernama : String
    lateinit var password :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataLogin = DatastoreLogin(this)
        dataLogin.userName.asLiveData().observe(this,{
            usernama = it.toString()
        })
        dataLogin.Pass.asLiveData().observe(this,{
            password = it.toString()
        })


        binding.btnRegis.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
//        binding.btnIndo.setOnClickListener {
//            setLocale("id")
//        }
//
//        binding.btning.setOnClickListener {
//            setLocale("en")
//        }

        binding.btnLogin.setOnClickListener {
            val edUser = binding.user.text.toString()
            val edPass = binding.pass.text.toString()
            if (edUser.isEmpty()){
                user.setError("isi username dahulu")
            }else if (edPass.isEmpty()){
                pass.setError("isi password dahulu")
            }else if (edUser == usernama){
                startActivity(Intent(this, HomeActivity::class.java))
                Toast.makeText(this, "anda berhasil login", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "Username dan password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
//    fun setLocale(lang: String?) {
//        val myLocale = Locale(lang)
//        val res = resources
//        val conf = res.configuration
//        conf.locale = myLocale
//        res.updateConfiguration(conf, res.displayMetrics)
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
//    }
}