package com.willyramad.logindatastore.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.willyramad.logindatastore.Adapter.AdapterCar
import com.willyramad.logindatastore.datastore.DatastoreLogin
import com.willyramad.logindatastore.databinding.ActivityHomeBinding
import com.willyramad.logindatastore.viewmodel.ViewModelCar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    lateinit var Car: AdapterCar
    lateinit var dataLogin : DatastoreLogin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Car = AdapterCar {  }

        dataLogin = DatastoreLogin(this)
        dataLogin.userName.asLiveData().observe(this,{
            binding.tvHeaderUser.text ="selamat datang" + it.toString()
        })
        binding.fav.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
        binding.profil.setOnClickListener {
            startActivity(Intent(this,ProfilActivity::class.java))
        }
        rvFilm.layoutManager = LinearLayoutManager(this)
        Car = AdapterCar (){
            val pindah = Intent(applicationContext, DetailActivity::class.java)
            pindah.putExtra("detail",it)
            pindah.putExtra(DetailActivity.EXTRA_ID, it.id)
            startActivity(pindah)
        }
        rvFilm.adapter = Car

        binding.fav.setOnClickListener {
            startActivity(Intent(this,FavoriteActivity::class.java))
        }
        setcallFilm()
    }
    fun setcallFilm(){
        val viewModel = ViewModelProvider(this).get(ViewModelCar::class.java)
        viewModel.callAllFillm()
        viewModel.getAllFilm().observe(this, Observer {
            if (it!=null)
                Car.Listcar(it)
                Car.notifyDataSetChanged()
        })

    }
}