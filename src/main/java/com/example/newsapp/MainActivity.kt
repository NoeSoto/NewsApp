package com.example.newsapp

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Adapters.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseURL = "https://newsapi.org"

class MainActivity : AppCompatActivity() {

    lateinit var countDownTimer: CountDownTimer

    private var titlesList = mutableListOf<String>()
    private var descriptionList = mutableListOf<String>()
    private var imagesList = mutableListOf<String>()
    private var linksList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeAPIRequest()
    }

    private fun setUpRecyclerView(){
        rv_recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        rv_recyclerView.adapter = RecyclerAdapter(titlesList, descriptionList, imagesList, linksList)

    }

    private fun addToList(title: String, description: String, image: String, link: String){
        titlesList.add(title)
        descriptionList.add(description)
        imagesList.add(image)
        linksList.add(link)
    }

    private fun makeAPIRequest(){
        val api = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getNews()

                for(article in response.articles){
                   Log.i("MainActivity", "Result = $article")
                    addToList(article.title,article.description,article.urlToImage,article.url)
                }

                withContext(Dispatchers.Main){
                    setUpRecyclerView()
                }

            }
            catch (e: Exception){
                Log.e("MainActivity", e.toString())
            }

        }
    }
}