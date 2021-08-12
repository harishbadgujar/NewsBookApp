package com.example.newsbookapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.newsbookapp.R
import com.example.newsbookapp.databinding.NewsItemBinding
import com.example.newsbookapp.model.NewsModel


class NewsAdapter(
    private var newsList: List<NewsModel>,
    private val onMovieClick: (NewsModel) -> Unit
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: NewsItemBinding =
            NewsItemBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position],onMovieClick)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class NewsViewHolder(private val itemBinding: NewsItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(news: NewsModel,onMovieClick: (NewsModel) -> Unit) {
            with(itemBinding) {

                upcomingParent.setOnClickListener {
                    onMovieClick(news)
                }
                ivNews.load(news.urlToImage) {
                    placeholder(R.drawable.ic_placeholder)
                    diskCachePolicy(CachePolicy.ENABLED)
                }
                tvTitle.text = news.title
                tvDescription.text = news.description
                tvDate.text = news.publishedAt
                tvSource.text = news.source?.name
                executePendingBindings()
            }
        }


    }
}