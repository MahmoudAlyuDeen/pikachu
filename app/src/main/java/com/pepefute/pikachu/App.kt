package com.pepefute.pikachu

import android.app.Application

import com.evernote.android.job.JobManager
import com.pepefute.pikachu.job.PokemonJobCreator

import io.realm.Realm
import io.realm.RealmConfiguration

@Suppress("unused")
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
        JobManager.create(this).addJobCreator(PokemonJobCreator())
    }
}
