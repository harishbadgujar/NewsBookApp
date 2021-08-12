package com.example.newsbookapp.dashboard.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsbookapp.adapter.BooksAdapter
import com.example.newsbookapp.databinding.BooksFragmentBinding
import com.example.newsbookapp.model.BookModel
import com.example.newsbookapp.network.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksFragment : Fragment() {
    private lateinit var binding: BooksFragmentBinding
    private val viewModel: BooksViewModel by viewModels()

    companion object {
        fun newInstance() = BooksFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = BooksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        activity?.let {
            viewModel.getBooks().observe(it) {
                it.let { resource ->
                    when (resource.status) {

                        Status.Success -> {
                            resource.data?.books?.let { books ->
                                binding.progressBar.isVisible = false
                                if (books.isNotEmpty()) {
                                    setNewsList(books)
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

    private fun setNewsList(booksList: List<BookModel>) {
        binding.rvBooks.setHasFixedSize(true)
        binding.rvBooks.layoutManager = GridLayoutManager(activity, 2)
        binding.rvBooks.adapter = BooksAdapter(booksList)
    }

}