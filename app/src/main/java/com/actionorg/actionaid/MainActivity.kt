package com.actionorg.actionaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var account_img : ImageView
    lateinit var donate_img : ImageView
    lateinit var home_img : ImageView
    lateinit var gallery_img : ImageView
    lateinit var news_img : ImageView
    lateinit var contact_img : ImageView
    lateinit var about_img : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //clicks()
       clicks()
    }
    private fun clicks(){
        home_img = findViewById(R.id.Home)
        gallery_img = findViewById(R.id.galleryIcon)
        donate_img = findViewById(R.id.volunteerIcon)
        news_img = findViewById(R.id.newsIcon)
        account_img = findViewById(R.id.accountIcon)
        contact_img = findViewById(R.id.contactIcon)
        about_img = findViewById(R.id.aboutIcon)
        //ClickListener to take you to selected activity
        home_img.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
        }
        //Gallery
        gallery_img.setOnClickListener {
            val galIntent = Intent(this, AIActivity::class.java)
            startActivity(galIntent)
        }
        //Account
        account_img.setOnClickListener {
            val accIntent = Intent(this, AccountActivity::class.java)
            startActivity(accIntent)
        }
        //Contact
        contact_img.setOnClickListener {
            val conIntent = Intent(this, ContactActivity::class.java)
            startActivity(conIntent)
        }
        //news
        news_img.setOnClickListener{
            val conIntent = Intent(this, NewsActivity::class.java)
            startActivity(conIntent)
        }
        //about
        about_img.setOnClickListener{
            val abIntent = Intent(this, AboutUsActivity::class.java)
            startActivity(abIntent)
        }
        //volunteer/donate
        donate_img.setOnClickListener {
            val donIntent = Intent(this, DonateActivity::class.java)
            startActivity(donIntent)
        }
    }
}