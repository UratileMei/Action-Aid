package com.actionorg.actionaid

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import android.webkit.WebView
import android.webkit.WebViewClient


class DonateActivity: AppCompatActivity() {
    lateinit var account_img : ImageView
    lateinit var donate_img : ImageView
    lateinit var home_img : ImageView
    lateinit var gallery_img : ImageView
    lateinit var news_img : ImageView
    lateinit var contact_img : ImageView
    lateinit var about_img : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)
        val webView: WebView = findViewById(R.id.payfastWebView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // PayFast sandbox URL with required parameters
        val payfastUrl = "https://sandbox.payfast.co.za/eng/process?" +
                "merchant_id=10004002" +
                "&merchant_key=q1cd2rdny4a53" +
                "&amount=100.00" + // Example amount; replace with actual donation amount
                "&item_name=Action Aid Donation" +
                "&return_url=https://yourapp.com/success" +
                "&cancel_url=https://yourapp.com/cancel"

        webView.loadUrl(payfastUrl)
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