package com.willyramad.logindatastore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.willyramad.logindatastore.Adapter.AdapterCar
import com.willyramad.logindatastore.databinding.ActivityDetailBinding
import com.willyramad.logindatastore.local.DatabaseCar
import com.willyramad.logindatastore.local.FavoriteCar
import com.willyramad.logindatastore.local.FavoriteDao
import com.willyramad.logindatastore.model.ResponCarItemItem
import com.willyramad.logindatastore.viewmodel.ViewModelCar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import okhttp3.internal.notifyAll


class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    private var carDao : FavoriteDao?=null
    private var carDb : DatabaseCar?=null
    private var id :Int?=null

    companion object{
        const val  EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carDb = DatabaseCar.getInstance(this)
        carDao = carDb?.carDao()
        id = intent.getIntExtra(EXTRA_ID, 0)

        val detailcar = intent.getSerializableExtra("detail") as ResponCarItemItem
        binding.tvNama.text = detailcar.name
        binding.tvkategori.text = detailcar.category
        binding.tvPrice.text = detailcar.price.toString()
        Glide.with(this).load(detailcar.image).into(Img)

        binding.fav.setOnClickListener {
            GlobalScope.async {
                val no = detailcar.id
                val nama = detailcar.name
                val kategori = detailcar.category
                val harga = detailcar.price.toString()
                val gambar = detailcar.image
                val tofav = carDb?.carDao()?.addToFav(FavoriteCar(no,gambar,nama,kategori,harga))

                runOnUiThread {
                    if ( tofav != 0.toLong()){
                        Toast.makeText(this@DetailActivity, "Berhasil Tambah ke Favorit", Toast.LENGTH_SHORT).show()
                        var _Cek = false
                        _Cek = !_Cek
                        fav.isChecked = _Cek
                    }else{
                        Toast.makeText(this@DetailActivity, "Gagagl tambah ke Favorit", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



    }
}