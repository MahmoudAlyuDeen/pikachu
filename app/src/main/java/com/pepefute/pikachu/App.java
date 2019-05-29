package com.pepefute.pikachu;

import android.app.Application;

import com.evernote.android.job.JobManager;
import com.pepefute.pikachu.job.PokemonJobCreator;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        JobManager.create(this).addJobCreator(new PokemonJobCreator());
    }
}
