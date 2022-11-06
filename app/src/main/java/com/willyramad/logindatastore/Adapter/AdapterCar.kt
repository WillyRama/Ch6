package com.willyramad.logindatastore.Adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.willyramad.logindatastore.databinding.ItemCarBinding
import com.willyramad.logindatastore.local.FavoriteCar
import com.willyramad.logindatastore.model.ResponCarItemItem
import com.willyramad.logindatastore.ui.DetailActivity

class AdapterCar(private var onClick : (ResponCarItemItem)->Unit) : RecyclerView.Adapter<AdapterCar.ViewHolder>() {

    private var listCar: List<ResponCarItemItem>? = null
    private var listFav :List<FavoriteCar>? = null
    fun Listcar(carList : List<ResponCarItemItem>){
        this.listCar = carList
    }

    class ViewHolder  (var binding : ItemCarBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCarBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNama.text = listCar!![position].name
        holder.binding.tvkategori.text = listCar!![position].category
        holder.binding.tvprice.text = listCar!![position].price.toString()
        Glide.with(holder.itemView).load(listCar!![position].image).into(holder.binding.Img)
        holder.binding.crFilm.setOnClickListener {
            onClick(listCar!![position])
        }
    }

    override fun getItemCount(): Int {
        if (listCar == null){
            return 0
        }else{
            return listCar!!.size
        }
    }
}