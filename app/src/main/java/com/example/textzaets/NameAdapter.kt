package com.example.textzaets

import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.textzaets.databinding.ViewLayoutBinding

class NameAdapter(val names: ArrayList<SpannableString>): RecyclerView.Adapter<NameHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_layout, parent, false)

        return NameHolder(view)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: NameHolder, position: Int) {
        holder.bind(names[position])
    }
}

class NameHolder(view: View) : RecyclerView.ViewHolder(view) {
    private lateinit var binding: ViewLayoutBinding

    fun bind(name: SpannableString) {
        binding = ViewLayoutBinding.bind(itemView)

        binding.nameHolder.text = name
    }
}