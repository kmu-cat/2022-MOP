package com.example.bori

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONObject

class DefaultCatSetting: AppCompatActivity(), View.OnClickListener {
    lateinit var catInfoJSON: JSONObject
    lateinit var myCat: ImageView
    lateinit var btnSamsagi: ImageButton
    lateinit var btnCheese: ImageButton
    lateinit var btnTiger: ImageButton
    lateinit var btnSnowwhite: ImageButton

    lateinit var prev: ImageButton
    lateinit var curr: ImageButton
    var cat_img = 0
    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_defaultcatsetting)

        catInfoJSON = JSONObject()

        val prefs: SharedPreferences = getSharedPreferences("CatInfo", 0)
        val editor = prefs.edit()
        val user = Firebase.auth.currentUser!!
        val email = user.email.toString()

        catInfoJSON.put("Color", R.drawable.cat_snowwhite)
        catInfoJSON.put("Hair", R.drawable.item_none)
        catInfoJSON.put("Face", R.drawable.item_none)
        catInfoJSON.put("Body", R.drawable.item_none)
        catInfoJSON.put("Foot", R.drawable.item_none)
        catInfoJSON.put("Etc", R.drawable.item_none)

        btnSamsagi = findViewById(R.id.samsagiBtn)
        btnSnowwhite = findViewById(R.id.snowwhiteBtn)
        btnCheese = findViewById(R.id.cheeseBtn)
        btnTiger = findViewById(R.id.tigerBtn)
        myCat = findViewById(R.id.my_cat)

        curr = btnSnowwhite

        btnSamsagi.setOnClickListener(this)
        btnCheese.setOnClickListener(this)
        btnSnowwhite.setOnClickListener(this)
        btnTiger.setOnClickListener(this)

        var pickUpBtn = findViewById<Button>(R.id.pickUpBtn)

        pickUpBtn.setOnClickListener {
            db.collection("users").document(email).update("catSettingDone", true)
            db.collection("users").document(email).update("catImg", cat_img)
            startActivity(Intent(this, Main::class.java))
            Toast.makeText(this@DefaultCatSetting, "야옹~",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Main::class.java)

            editor.putString("CatInfo", catInfoJSON.toString())
            editor.apply()

            startActivity(intent)
        }
    }

    override fun onClick(selected: View?) {
        if (selected != null) {
            prev = curr
            when (selected.id) {
                R.id.samsagiBtn -> {
                    catInfoJSON.remove("Color")
                    catInfoJSON.put("Color", R.drawable.cat_samsagi)
                    myCat.setImageResource(R.drawable.cat_samsagi)
                    curr = btnSamsagi
                    cat_img = 0
                }
                R.id.cheeseBtn -> {
                    catInfoJSON.remove("Color")
                    catInfoJSON.put("Color", R.drawable.cat_cheese)
                    myCat.setImageResource(R.drawable.cat_cheese)
                    curr = btnCheese
                    cat_img = 1
                }
                R.id.snowwhiteBtn -> {
                    catInfoJSON.remove("Color")
                    catInfoJSON.put("Color", R.drawable.cat_snowwhite)
                    myCat.setImageResource(R.drawable.cat_snowwhite)
                    curr = btnSnowwhite
                    cat_img = 2
                }
                R.id.tigerBtn -> {
                    catInfoJSON.remove("Color")
                    catInfoJSON.put("Color", R.drawable.cat_tiger)
                    myCat.setImageResource(R.drawable.cat_tiger)
                    curr = btnTiger
                    cat_img = 3
                }
            }
            when (prev.id) {
                R.id.samsagiBtn -> btnSamsagi.setImageResource(R.drawable.color_samsagi)
                R.id.tigerBtn -> btnTiger.setImageResource(R.drawable.color_tiger)
                R.id.snowwhiteBtn -> btnSnowwhite.setImageResource(R.drawable.color_snowwhite)
                R.id.cheeseBtn -> btnCheese.setImageResource(R.drawable.color_cheese)
            }
            when (curr.id) {
                R.id.samsagiBtn -> btnSamsagi.setImageResource(R.drawable.color_samsagi_clk)
                R.id.tigerBtn -> btnTiger.setImageResource(R.drawable.color_tiger_clk)
                R.id.snowwhiteBtn -> btnSnowwhite.setImageResource(R.drawable.color_snowwhite_clk)
                R.id.cheeseBtn -> btnCheese.setImageResource(R.drawable.color_cheese_clk)
            }
        }
    }
}



