package com.example.messengerapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.messengerapp.R
import com.example.messengerapp.ui.fragment.MessengerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.rootContainer, MessengerFragment())
                .commitAllowingStateLoss()
        }
    }
}