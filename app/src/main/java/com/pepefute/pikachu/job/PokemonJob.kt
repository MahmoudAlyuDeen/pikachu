package com.pepefute.pikachu.job

import com.evernote.android.job.Job
import com.pepefute.pikachu.beans.RealmPokemon
import io.realm.Realm
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient
import org.greenrobot.eventbus.EventBus

class PokemonJob : Job() {

    override fun onRunJob(params: Params): Result {
        val pokeApi = PokeApiClient()

        val realm = Realm.getDefaultInstance()
        val realmPokemons = realm.where(RealmPokemon::class.java).findAll()
        if (realmPokemons.size > 10) {
            realm.close()
            EventBus.getDefault().post(PokemonLoadingFinished())
            return Result.SUCCESS
        }

        for (i in 1..99) {
            val (id, name, _, height, _, _, weight, _, _, _, _, _, _, _, types, sprites) = pokeApi.getPokemon(i)
            val realmPokemon = RealmPokemon(id,
                    name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(),
                    height,
                    types[0].type.name,
                    weight,
                    sprites.frontDefault!!)
            realm.executeTransaction { transactionRealm -> transactionRealm.copyToRealmOrUpdate(realmPokemon) }
        }
        realm.close()
        EventBus.getDefault().post(PokemonLoadingFinished())
        return Result.SUCCESS
    }

    class PokemonLoadingFinished

    companion object {
        const val TAG = "job_pokemon_tag"
    }
}
