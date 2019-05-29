package com.pepefute.pikachu.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.evernote.android.job.JobRequest;
import com.pepefute.pikachu.R;
import com.pepefute.pikachu.beans.RealmPokemon;
import com.pepefute.pikachu.job.PokemonJob;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_toolbar)
    Toolbar mHomeToolbar;
    @BindView(R.id.home_pokemon_recycler)
    RecyclerView mHomePokemonRecycler;
    @BindView(R.id.home_pokemon_progress)
    ProgressBar mHomePokemonProgress;

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new JobRequest.Builder(PokemonJob.TAG)
                .startNow()
                .build()
                .schedule();

        mRealm = Realm.getDefaultInstance();

        RealmResults<RealmPokemon> pokemons = mRealm.where(RealmPokemon.class).findAll();

        PokemonAdapter adapter = new PokemonAdapter(pokemons, this);

        mHomePokemonRecycler.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PokemonJob.PokemonLoadingFinished event) {
        mHomePokemonProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        mRealm.close();
        super.onDestroy();
    }
}
