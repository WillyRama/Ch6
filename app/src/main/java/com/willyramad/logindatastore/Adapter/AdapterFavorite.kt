package com.willyramad.logindatastore.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.willyramad.logindatastore.databinding.ItemCarBinding
import com.willyramad.logindatastore.local.FavoriteCar

class AdapterFavorite(val listFavoriteCar: List<FavoriteCar>):RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {
    class ViewHolder(var binding : ItemCarBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCarBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNama.text = listFavoriteCar!![position].nama
        holder.binding.tvkategori.text = listFavoriteCar!![position].category
        holder.binding.tvprice.text = listFavoriteCar!![position].price.toString()
        Glide.with(holder.itemView).load(listFavoriteCar!![position].image).into(holder.binding.Img)
    }

    override fun getItemCount(): Int {
       return  listFavoriteCar.size
    }
}