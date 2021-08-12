package com.example.newsbookapp.dashboard.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newsbookapp.activity.NewsDetailsActivity
import com.example.newsbookapp.adapter.NewsAdapter
import com.example.newsbookapp.databinding.NewsFragmentBinding
import com.example.newsbookapp.model.NewsModel
import com.example.newsbookapp.network.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    lateinit var binding: NewsFragmentBinding
    private val viewModel: NewsViewModel by viewModels()

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        activity?.let {
            viewModel.getHeadlines().observe(it) {
                it.let { resource ->
                    when (resource.status) {

                        Status.Success -> {
                            resource.data?.articles?.let { articles ->
                                binding.progressBar.isVisible = false
                                if (articles.isNotEmpty()) {
                                    setNewsList(articles)
                                }
                            }
                        }

                        Status.Error -> {
                            Toast.makeText(activity, resource.message, Toast.LENGTH_LONG).show()
                        }

                        Status.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.progressBar.bringToFront()
                            binding.progressBar.animate()
                        }
                    }
                }
            }

        }
    }

    private fun setNewsList(newsList: List<NewsModel>) {
        binding.rvNews.setHasFixedSize(true)
        binding.rvNews.adapter = NewsAdapter(newsList,onMovieClick)
    }

    private val onMovieClick: (NewsModel) -> Unit = { news ->
        Intent(activity, NewsDetailsActivity::class.java).apply {
            putExtra(NewsDetailsActivity.NEWS, news)
            startActivity(this)
        }
    }
}