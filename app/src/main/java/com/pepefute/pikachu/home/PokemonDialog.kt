package com.pepefute.pikachu.home

import android.content.Context
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.github.javiersantos.materialstyleddialogs.enums.Style
import com.pepefute.pikachu.R
import com.pepefute.pikachu.beans.RealmPokemon
import kotlinx.android.synthetic.main.pokemon_dialog_view.view.*

class PokemonDialog(pokemon: RealmPokemon, context: Context) {

    init {
        val dialog = LayoutInflater.from(context).inflate(R.layout.pokemon_dialog_view, null)

        dialog.dialogPokemonNameTextView.text = pokemon.name
        dialog.dialogPokemonTypeTextView.text = pokemon.type
        dialog.dialogPokemonHeightTextView.text = pokemon.height.toString()
        dialog.dialogPokemonWeightTextView.text = pokemon.weight.toString()
        Glide.with(context).load(pokemon.sprite).into(dialog.dialogPokemonLogoImageView)

        MaterialStyledDialog.Builder(context)
                .setStyle(Style.HEADER_WITH_TITLE)
                .setTitle(pokemon.name.toString())
                .setCustomView(dialog)
                .show()
    }
}
