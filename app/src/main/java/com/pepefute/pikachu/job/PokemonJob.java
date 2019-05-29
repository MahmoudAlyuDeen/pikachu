package com.pepefute.pikachu.job;

import com.evernote.android.job.Job;
import com.pepefute.pikachu.beans.RealmPokemon;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import io.realm.Realm;
import io.realm.RealmResults;
import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;

public class PokemonJob extends Job {

    public static final String TAG = "job_pokemon_tag";

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        PokeApi pokeApi = new PokeApiClient();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<RealmPokemon> realmPokemons = realm.where(RealmPokemon.class).findAll();
        if (realmPokemons.size() > 10) {
            realm.close();
            EventBus.getDefault().post(new PokemonLoadingFinished());
            return Result.SUCCESS;
        }

        for (int i = 1; i < 100; i++) {
            Pokemon pokemon = pokeApi.getPokemon(i);
            RealmPokemon realmPokemon = new RealmPokemon(pokemon.getId(),
                    pokemon.getName().substring(0, 1).toUpperCase() + pokemon.getName().substring(1).toLowerCase(),
                    pokemon.getHeight(),
                    pokemon.getTypes().get(0).getType().getName(),
                    pokemon.getWeight(),
                    pokemon.getSprites().getFrontDefault());
            realm.executeTransaction(transactionRealm -> transactionRealm.copyToRealmOrUpdate(realmPokemon));
        }
        realm.close();
        EventBus.getDefault().post(new PokemonLoadingFinished());
        return Result.SUCCESS;
    }

    public static class PokemonLoadingFinished {
    }
}
