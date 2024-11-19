package com.actionorg.actionaid

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NewsActivity : AppCompatActivity() {
    lateinit var account_img : ImageView
    lateinit var donate_img : ImageView
    lateinit var home_img : ImageView
    lateinit var gallery_img : ImageView
    lateinit var news_img : ImageView
    lateinit var contact_img : ImageView
    lateinit var about_img : ImageView
    //
    private lateinit var database: DatabaseReference
    private lateinit var newsList: MutableList<NewsItem>
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        clicks()

        database = FirebaseDatabase.getInstance().getReference("news")
        newsList = mutableListOf()
        newsAdapter = NewsAdapter(newsList)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = newsAdapter

        fetchNewsData()

    }
    private fun fetchNewsData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                newsList.clear()
                for (newsSnapshot in snapshot.children) {
                    val newsItem = newsSnapshot.getValue(NewsItem::class.java)
                    newsItem?.let { newsList.add(it) }
                }
                newsAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
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