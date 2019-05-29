package com.pepefute.pikachu.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.pepefute.pikachu.R;
import com.pepefute.pikachu.beans.RealmPokemon;

import butterknife.BindView;
import butterknife.ButterKnife;

class PokemonDialog {

    @BindView(R.id.dialog_pokemon_name_text_view)
    TextView mDialogPokemonNameTextView;
    @BindView(R.id.dialog_pokemon_logo_image_view)
    ImageView mDialogPokemonLogoImageView;
    @BindView(R.id.dialog_pokemon_type_text_view)
    TextView mDialogPokemonTypeTextView;
    @BindView(R.id.dialog_pokemon_height_text_view)
    TextView mDialogPokemonHeightTextView;
    @BindView(R.id.dialog_pokemon_weight_text_view)
    TextView mDialogPokemonWeightTextView;

    PokemonDialog(RealmPokemon pokemon, Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View dialog = inflater.inflate(R.layout.pokemon_dialog_view, null);
        ButterKnife.bind(this, dialog);

        mDialogPokemonNameTextView.setText(pokemon.getName());
        mDialogPokemonTypeTextView.setText(pokemon.getType());
        mDialogPokemonHeightTextView.setText(String.valueOf(pokemon.getHeight()));
        mDialogPokemonWeightTextView.setText(String.valueOf(pokemon.getWeight()));
        Glide.with(context).load(pokemon.getSprite()).into(mDialogPokemonLogoImageView);

        new MaterialStyledDialog.Builder(context)
                .setStyle(Style.HEADER_WITH_TITLE)
                .setTitle(pokemon.getName())
                .setCustomView(dialog)
                .show();

    }
}
