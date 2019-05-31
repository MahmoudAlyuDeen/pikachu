package com.pepefute.pikachu.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.evernote.android.job.JobRequest
import com.pepefute.pikachu.R
import com.pepefute.pikachu.beans.RealmPokemon
import com.pepefute.pikachu.job.PokemonJob
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    lateinit var mRealm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        JobRequest.Builder(PokemonJob.TAG)
                .startNow()
                .build()
                .schedule()

        mRealm = Realm.getDefaultInstance()

        val pokemons = mRealm.where(RealmPokemon::class.java).findAll()

        homePokemonRecycler.adapter = PokemonAdapter(pokemons, this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: PokemonJob.PokemonLoadingFinished) {
        homePokemonProgress.visibility = View.INVISIBLE
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        mRealm.close()
        super.onDestroy()
    }

}