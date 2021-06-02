package com.kaverikr.nationalflag.nationalflag.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kaverikr.nationalflag.R
import com.kaverikr.nationalflag.nationalflag.models.Country
import com.kaverikr.nationalflag.nationalflag.getProgressDrawable
import com.kaverikr.nationalflag.nationalflag.loadImage
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(var list: ArrayList<Country>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    )

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateList(it: List<Country>) {
        list.addAll(it)
        notifyDataSetChanged()
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val imageView = view.imageView
        private val countryName = view.name
        private val countryCapital = view.capital
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            countryName.text = country.articles.get(adapterPosition).title
            countryCapital.text = country.articles.get(adapterPosition).description
            imageView.loadImage(country.articles.get(adapterPosition).urlToImage, progressDrawable)
        }

    }


}