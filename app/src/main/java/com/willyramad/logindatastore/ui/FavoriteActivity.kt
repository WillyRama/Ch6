package com.willyramad.logindatastore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.willyramad.logindatastore.Adapter.AdapterFavorite
import com.willyramad.logindatastore.R
import com.willyramad.logindatastore.databinding.ActivityFavoriteBinding
import com.willyramad.logindatastore.local.DatabaseCar
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {

    lateinit var binding : ActivityFavoriteBinding
    private var DbCar : DatabaseCar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DbCar = DatabaseCar.getInstance(this)

        rvFav.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        GlobalScope.launch {
            val ListFav = DbCar?.carDao()?.getFavCar()
            runOnUiThread {
                ListFav.let {
                    rvFav.adapter = AdapterFavorite(it!!)
                }
            }
        }
    }
}