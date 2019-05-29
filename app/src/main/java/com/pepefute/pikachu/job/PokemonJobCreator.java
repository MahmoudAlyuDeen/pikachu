package com.pepefute.pikachu.job;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PokemonJobCreator implements JobCreator {

    @Override
    @Nullable
    public Job create(@NonNull String tag) {
        switch (tag) {
            case PokemonJob.TAG:
                return new PokemonJob();
            default:
                return null;
        }
    }
}
