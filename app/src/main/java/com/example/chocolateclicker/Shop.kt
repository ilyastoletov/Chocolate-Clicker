package com.example.chocolateclicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class Shop : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.shop_anim)
        val backBtn: ImageView = findViewById(R.id.backBtn)
        backBtn.setOnClickListener { v ->
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }

        val autoclickerBtn: Button = findViewById(R.id.buyAutoclicker)
        autoclickerBtn.setOnClickListener { v ->
            autoclickerBtn.startAnimation(anim)
        }

        val factoryBtn: Button = findViewById(R.id.buyFactory)
        factoryBtn.setOnClickListener { v ->
            factoryBtn.startAnimation(anim)
        }

        val cityBtn: Button = findViewById(R.id.buyCity)
        cityBtn.setOnClickListener { v ->
            cityBtn.startAnimation(anim)
        }

        val godBtn: Button = findViewById(R.id.buyGod)
        godBtn.setOnClickListener { v ->
            godBtn.startAnimation(anim)
        }
    }
}