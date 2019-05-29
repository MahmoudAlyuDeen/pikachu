package com.pepefute.pikachu.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pepefute.pikachu.R;
import com.pepefute.pikachu.beans.RealmPokemon;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

class PokemonAdapter extends RealmRecyclerViewAdapter<RealmPokemon, PokemonAdapter.PokemonViewHolder> {

    private final Context mContext;

    PokemonAdapter(@Nullable OrderedRealmCollection<RealmPokemon> data, Context context) {
        super(data, true);
        mContext = context;
    }

    @NotNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NotNull PokemonViewHolder holder, int position) {
        RealmPokemon pokemon = getData().get(position);
        if (pokemon != null) {
            holder.setPokemon(pokemon);
        }
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(getData()).size();
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RealmPokemon mPokemon;

        @BindView(R.id.item_pokemon_parent)
        RelativeLayout mItemPokemonParent;
        @BindView(R.id.item_pokemon_logo_image_view)
        ImageView mItemPokemonLogoImageView;
        @BindView(R.id.item_pokemon_title_text_view)
        TextView mItemPokemonTitleTextView;
        @BindView(R.id.item_pokemon_subtitle_text_view)
        TextView mItemPokemonSubtitleTextView;

        PokemonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mItemPokemonParent.setOnClickListener(this);
        }

        void setPokemon(RealmPokemon pokemon) {
            mItemPokemonTitleTextView.setText(pokemon.getName());
            mItemPokemonSubtitleTextView.setText(pokemon.getType());
            Glide.with(mContext).load(pokemon.getSprite()).into(mItemPokemonLogoImageView);
            mPokemon = pokemon;
        }

        @Override
        public void onClick(View v) {
            new PokemonDialog(mPokemon, mContext);
        }
    }
}
