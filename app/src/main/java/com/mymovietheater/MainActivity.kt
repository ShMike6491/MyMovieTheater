package com.mymovietheater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mymovietheater.ui.category.CategoryFragment
import com.mymovietheater.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CategoryFragment())
                .commitNow()
        }
    }
}