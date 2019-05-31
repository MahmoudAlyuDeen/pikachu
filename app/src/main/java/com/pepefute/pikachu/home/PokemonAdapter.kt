package com.pepefute.pikachu.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pepefute.pikachu.R
import com.pepefute.pikachu.beans.RealmPokemon
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(pokemonData: OrderedRealmCollection<RealmPokemon>,
                     private val mContext: Context) : RealmRecyclerViewAdapter<RealmPokemon,
        PokemonAdapter.PokemonViewHolder>(pokemonData, true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        if (pokemon != null) {
            holder.setPokemon(pokemon, mContext)
        }
    }

    override fun getItemCount(): Int {
        return if (data.isNullOrEmpty()) {
            0
        } else {
            data!!.size
        }
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setPokemon(pokemon: RealmPokemon, mContext: Context) {
            itemView.itemPokemonTitleTextView.text = pokemon.name
            itemView.item_pokemonSubtitleTextView.text = pokemon.type
            Glide.with(mContext).load(pokemon.sprite).into(itemView.itemPokemonLogoImageView)
            itemView.setOnClickListener { PokemonDialog(pokemon, mContext) }
        }
    }
}
