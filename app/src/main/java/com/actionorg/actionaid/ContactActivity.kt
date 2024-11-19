package com.actionorg.actionaid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class ContactActivity : AppCompatActivity() {
    lateinit var account_img : ImageView
    lateinit var donate_img : ImageView
    lateinit var home_img : ImageView
    lateinit var gallery_img : ImageView
    lateinit var news_img : ImageView
    lateinit var contact_img : ImageView
    lateinit var about_img : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        var mainActivity: MainActivity
        clicks()
        val name = findViewById<TextView>(R.id.name_txt)
        val phone = findViewById<TextView>(R.id.phone_txt)
        val email = findViewById<TextView>(R.id.email_txt)
        val city = findViewById<TextView>(R.id.city_txt)
        val btn = findViewById<Button>(R.id.Send)
        val message = findViewById<EditText>(R.id.msg)
        val image = findViewById<ImageView>(R.id.imageView)

        //database connect
        val database = FirebaseDatabase.getInstance().getReference("Inquiry")
        //val mesRef = database.getReference("message")
       //val phoRef = database.getReference("phone")
        //val namRef = database.getReference("name")
        //val citRef = database.getReference("city")
        //val emaRef = database.getReference("email")
        //Send button click listener
        btn.setOnClickListener {
            val name = name.text.toString().trim()
            val email = email.text.toString().trim()
            val message = message.text.toString().trim()
            val city = city.text.toString().trim()
            val phone = phone.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || message.isEmpty() || city.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                val contactInfo = ContactInfo(name, email, message, city, phone)
                database.push().setValue(contactInfo).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Message sent successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed to send message", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
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
