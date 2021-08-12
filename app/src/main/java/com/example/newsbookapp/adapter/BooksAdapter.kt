package com.example.newsbookapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsbookapp.databinding.BooksItemBinding
import com.example.newsbookapp.model.BookModel


class BooksAdapter(
        private var booksList: List<BookModel>
) :
        RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: BooksItemBinding =
                BooksItemBinding.inflate(layoutInflater, parent, false)
        return BooksViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(booksList[position])
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    class BooksViewHolder(private val itemBinding: BooksItemBinding) :
            RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(book: BookModel) {
            with(itemBinding) {
                ivBook.load(book.volumeInfo.imageLinks.thumbnail.replace("http","https"))
                executePendingBindings()
            }
        }
    }
}