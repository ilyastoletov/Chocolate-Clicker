package com.example.chocolateclicker

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var counter: Int = 0
    lateinit var counterText: TextView
    var pref: SharedPreferences? = null
    var threadStarted: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterText = findViewById(R.id.balance)
        pref = getSharedPreferences("MAIN_CHOCOLATE", MODE_PRIVATE)
        counter = pref?.getInt("counter", 0)!!
        counterText.text = counter.toString()

        val shopBtnAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.shop_btn_anim)
        val shopBtn: Button = findViewById(R.id.shopBtn2)
        val chocolateBtn: ImageView = findViewById(R.id.chocolateBtn)

        shopBtn.setOnClickListener { v ->
            v.startAnimation(shopBtnAnim)
            val shopIntent = Intent(this, Shop::class.java)
            startActivity(shopIntent)
        }

        val chocolateAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.chocolate_anim)
        chocolateBtn.setOnClickListener { v ->
                v.startAnimation(chocolateAnim)
                counter += 500
                counterText.text = counter.toString()
        }
        threadStarted = true
        startThread()
    }

    override fun onResume() {
        super.onResume()
        threadStarted = true
        counter = pref?.getInt("counter", 0)!!
        counterText.text = counter.toString()
    }

    fun saveData(key: String, value: Int) {
        val editor = pref?.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    fun startThread() {
        if (threadStarted) {
            Thread(Runnable {
                while (threadStarted) {
                    val autoClickerCount: Int = pref?.getInt("autoclickerCount", 0)!!
                    val factoryCount: Int = pref?.getInt("factoryCount", 0)!!
                    val cityCount: Int = pref?.getInt("cityCounter", 0)!!
                    val godCount: Int = pref?.getInt("godCounter", 0)!!
                    /*println("$autoClickerCount, $factoryCount, $cityCount, $godCount")*/
                    Thread.sleep(1000)
                    counter += autoClickerCount * 1
                    counter += factoryCount * 50
                    counter += cityCount * 500
                    counter += godCount * 10000
                    runOnUiThread(Runnable {
                        counterText.text = counter.toString()
                    })
                }
            }).start()
        }
    }

    override fun onPause() {
        super.onPause()
        saveData("counter", counter)
    }

    override fun onDestroy() {
        super.onDestroy()
        threadStarted = false
    }
}