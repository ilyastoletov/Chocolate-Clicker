package com.example.chocolateclicker

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class Shop : AppCompatActivity() {

    var pref: SharedPreferences? = null
    var balance: Int = 0
    lateinit var balanceText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)
        pref = getSharedPreferences("MAIN_CHOCOLATE", MODE_PRIVATE)
        balanceText = findViewById(R.id.balance)
        balance = pref?.getInt("counter", 0)!!
        print(balance)
        balanceText.text = balance.toString()

        val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.shop_anim)
        val backBtn: ImageView = findViewById(R.id.backBtn)
        backBtn.setOnClickListener { v ->
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }

        val autoclickerBtn: Button = findViewById(R.id.buyAutoclicker)
        autoclickerBtn.setOnClickListener { v ->
            autoclickerBtn.startAnimation(anim)
            buyUpgrade("autoclickerCount", 500, findViewById<TextView>(R.id.autoclickerCounter))
        }

        val factoryBtn: Button = findViewById(R.id.buyFactory)
        factoryBtn.setOnClickListener { v ->
            factoryBtn.startAnimation(anim)
            buyUpgrade("factoryCount", 500, findViewById<TextView>(R.id.autoclickerCounter))
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

    fun saveData(key: String, value: Int) {
        val editor = pref?.edit()
        editor?.putInt(key, value)
        editor?.apply()
    }

    fun buyUpgrade(name: String, price: Int, text: TextView) {
        if (balance >= price) {
            var upgradeCounter = pref?.getInt(name, 0)!!
            upgradeCounter++
            balance -= price
            saveData(name, upgradeCounter)
            saveData("counter", balance)
            Toast.makeText(this, "Upgrade buyed Successfully", Toast.LENGTH_SHORT).show()
            text.text = upgradeCounter.toString()
        } else {
            Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show()
        }
    }
}