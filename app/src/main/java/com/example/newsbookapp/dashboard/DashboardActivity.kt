package com.example.newsbookapp.dashboard

import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.newsbookapp.BaseActivity
import com.example.newsbookapp.R
import com.example.newsbookapp.dashboard.books.BooksFragment
import com.example.newsbookapp.dashboard.news.NewsFragment
import com.example.newsbookapp.databinding.DashboardActivityBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardActivity : BaseActivity() {
    lateinit var binding: DashboardActivityBinding
    val viewModel: DashboardViewModel by viewModels()

    override fun initializeViewBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.dashboard_activity)
        with(binding) {
            val newsFragment = NewsFragment.newInstance()
            val booksFragment = BooksFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, newsFragment)
                    .commit()
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, booksFragment)
                    .commit()

            bottomNavigation.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.news -> {
                        supportFragmentManager.beginTransaction().show(newsFragment).commit()
                        supportFragmentManager.beginTransaction().hide(booksFragment).commit()
                    }

                    R.id.books -> {
                        supportFragmentManager.beginTransaction().show(booksFragment).commit()
                        supportFragmentManager.beginTransaction().hide(newsFragment).commit()
                    }
                }
                return@setOnNavigationItemSelectedListener true
            }

            supportFragmentManager.beginTransaction().show(newsFragment).commit()
            supportFragmentManager.beginTransaction().hide(booksFragment).commit()
        }
    }

    override fun observeViewModel() {

    }

}