package com.example.newsbookapp.activity

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.newsbookapp.R
import com.example.newsbookapp.WebViewController
import com.example.newsbookapp.model.NewsModel


class NewsDetailsActivity : AppCompatActivity() {

    lateinit var news: NewsModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        intent?.let { intent ->
            intent.getParcelableExtra<NewsModel>(NEWS)?.let {
                news = it
            }
        }



        val webView: WebView = findViewById(R.id.web_view_zee)

        webView.loadUrl(news.url)
       // webView.loadUrl("https://zeenews.india.com/")

        // WebViewController is used

        // WebViewController is used
        webView.webViewClient = WebViewController()


    }

    companion object {
        const val NEWS = "news"
    }
}