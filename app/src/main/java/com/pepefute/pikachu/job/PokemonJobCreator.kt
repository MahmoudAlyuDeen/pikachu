package com.pepefute.pikachu.job

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator

class PokemonJobCreator : JobCreator {

    override fun create(tag: String): Job? {
        return when (tag) {
            PokemonJob.TAG -> PokemonJob()
            else -> null
        }
    }
}
