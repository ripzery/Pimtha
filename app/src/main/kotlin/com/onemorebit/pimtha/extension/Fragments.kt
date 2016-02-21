package com.onemorebit.pimtha.extension

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import com.onemorebit.pimtha.R
import timber.log.Timber

/**
 * Created by Euro on 2/21/16 AD.
 */

fun Fragment.log(msg: String? = "Please enter log"){
    Timber.d(msg)
}

fun <T> Fragment.save(id: String = "id", i : T){
    var sp: SharedPreferences = activity.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
    var editor = sp.edit()
    when (i){
        is Int -> editor.putInt(id, i)
        is String -> editor.putString(id, i)
        is Long -> editor.putLong(id, i)
        is Boolean -> editor.putBoolean(id, i)
        else -> throw IllegalArgumentException("Can save only [Int, String, Long, Boolean] !")
    }

    editor.commit()
}

fun <T> Fragment.get(id: String = "id", defaultValue : T) : Any {
    var sp: SharedPreferences = activity.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
    when(defaultValue){
        is Int -> return sp.getInt(id, defaultValue)
        is String -> return sp.getString(id, defaultValue)
        is Long -> return sp.getLong(id, defaultValue)
        is Boolean -> return sp.getBoolean(id, defaultValue)
        is Float -> return sp.getFloat(id, defaultValue)
        else -> throw IllegalArgumentException("Can get only [Int, String, Long, Boolean, Float] !")
    }
}