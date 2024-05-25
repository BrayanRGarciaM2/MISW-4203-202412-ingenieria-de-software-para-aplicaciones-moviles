package com.tsdc.vinilos.data.local.login

import android.content.Context

class LocalLoginDataSource(private val context: Context) {
    val sharedPreferences = context.getSharedPreferences("collector_data", Context.MODE_PRIVATE)

    fun saveCollectorId(id:Int) {
        sharedPreferences.edit().putInt("id", id).apply()
    }

    fun getCollectorId():Int {
        return sharedPreferences.getInt("id", -1)
    }
}
