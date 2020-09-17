package com.example.codingchallenge.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codingchallenge.R
import com.example.codingchallenge.models.Character
import kotlinx.android.synthetic.main.row_character.view.*

/**
 * Bridge between Character data and view
 */
class CharacterAdapter(private val mContext: Context)
    :RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var myList = ArrayList<Character>()

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(character:Character){
            itemView.tv_character_name.text = character.name
            itemView.tv_character_species.text = character.species
            itemView.tv_character_status.text = character.status

            Glide.with(mContext)
                .load(character.image)
                .into(itemView.image_character)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_character, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var character = myList[position]
        holder.bind(character)
    }
}