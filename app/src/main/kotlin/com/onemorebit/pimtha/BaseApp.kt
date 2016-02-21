package com.onemorebit.pimtha

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.onemorebit.pimtha.extension.get
import com.onemorebit.pimtha.extension.log
import com.onemorebit.pimtha.extension.save
import timber.log.Timber

/**
 * Created by Euro on 2/19/16 AD.
 */
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        if(get("id", 0L) as Long == 0L) {
            save("id", System.nanoTime())
        }
    }
}
