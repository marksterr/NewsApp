package com.markdawes.newsapp.ui.articlescreen

import android.view.View

class LeakySingleton {
    companion object {
        private var instance: LeakySingleton? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: LeakySingleton().also { instance = it }
        }
    }

    var viewReference: View? = null
}