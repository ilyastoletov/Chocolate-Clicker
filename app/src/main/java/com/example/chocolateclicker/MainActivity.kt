package com.example.chocolateclicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shopBtnAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.shop_btn_anim)
        val shopBtn: Button = findViewById(R.id.shopBtn2)
        shopBtn.setOnClickListener { v ->
            v.startAnimation(shopBtnAnim)
            val shopIntent = Intent(this, Shop::class.java)
            startActivity(shopIntent)
        }
    }
}