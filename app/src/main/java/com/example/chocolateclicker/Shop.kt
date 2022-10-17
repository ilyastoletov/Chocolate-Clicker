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
        balanceText.text = balance.toString()

        val autoCounter = findViewById<TextView>(R.id.autoclickerCounter)
        val facCounter = findViewById<TextView>(R.id.factoryCounter)
        val cityCounter = findViewById<TextView>(R.id.cityCounter)
        val godCounter = findViewById<TextView>(R.id.godCounter)

        autoCounter.text = pref?.getInt("autoclickerCount", 0)!!.toString()
        facCounter.text = pref?.getInt("factoryCount", 0)!!.toString()
        cityCounter.text = pref?.getInt("cityCounter", 0)!!.toString()
        godCounter.text = pref?.getInt("godCounter", 0)!!.toString()

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
            buyUpgrade("factoryCount", 5000, findViewById<TextView>(R.id.factoryCounter))
        }

        val cityBtn: Button = findViewById(R.id.buyCity)
        cityBtn.setOnClickListener { v ->
            cityBtn.startAnimation(anim)
            buyUpgrade("cityCounter", 45000, findViewById<TextView>(R.id.cityCounter))
        }

        val godBtn: Button = findViewById(R.id.buyGod)
        godBtn.setOnClickListener { v ->
            godBtn.startAnimation(anim)
            buyUpgrade("godCounter", 150000, findViewById<TextView>(R.id.godCounter))
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
            saveData("counter", balance)
            Toast.makeText(this, "Upgrade buyed Successfully", Toast.LENGTH_SHORT).show()
            saveData(name, upgradeCounter)
            text.text = upgradeCounter.toString()
            balanceText.text = balance.toString()
        } else {
            Toast.makeText(this, "Not enough money!", Toast.LENGTH_SHORT).show()
        }
    }
}