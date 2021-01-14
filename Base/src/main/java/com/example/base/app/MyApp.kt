package com.shop.app

import android.app.Application
import com.example.basemvvm.utils.MyMmkv

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        map = HashMap()
        MyMmkv.initMMKV()
    }

    companion object{
        var instance:MyApp? = null
        var map:HashMap<String,Any>? = null
    }

}