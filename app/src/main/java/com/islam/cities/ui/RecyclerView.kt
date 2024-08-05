package com.islam.cities.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.islam.cities.R
import com.islam.cities.data.model.CityModel
import com.islam.cities.databinding.CityItemBinding


class MyRecyclerView(var list: List<CityModel>) :
    RecyclerView.Adapter<MyRecyclerView.MyViewHolder>(), Filterable {
    init {
        list = list.sortedBy { it.name }
    }

    private var itemListFull: List<CityModel> = list.toList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: CityItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.city_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var model = list.get(position)
        holder.binding.title.text = model.name + ", " + model.country
        holder.binding.subtitle.text =
            "lon: ${model.coord.lon.toString()}   lat: ${model.coord.lat.toString()}"
    }

    class MyViewHolder(binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: CityItemBinding = binding
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<CityModel>()

                if (constraint.isNullOrEmpty()) {
                    filteredList.addAll(itemListFull)
                } else {
                    val filterPattern = constraint.toString().lowercase().trim()

                    for (item in itemListFull) {
                        if (item.name!!.lowercase().startsWith(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }
                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                list = (results?.values as List<CityModel>).sortedBy { it.name }
                notifyDataSetChanged()
            }
        }
    }
}