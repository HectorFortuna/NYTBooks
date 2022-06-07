package com.hectorfortuna.nytbooks.presentation.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.hectorfortuna.nytbooks.data.model.Book
import com.hectorfortuna.nytbooks.databinding.ItemBookBinding


class BooksAdapter(
    private val books: List<Book>,
    val onItemClickListener: ((book: Book) -> Unit)
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): BooksViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding,onItemClickListener)
    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(viewHolder: BooksViewHolder, position: Int) {
        viewHolder.bindView(books[position])
    }

    class BooksViewHolder(
        binding: ItemBookBinding,
        private val onItemClickListener: ((book: Book) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {

        private val title = binding.textTitle
        private val author = binding.textAuthor

        fun bindView(book: Book) {
            title.text = book.title
            author.text = book.author

            itemView.setOnClickListener {
                onItemClickListener.invoke(book)
            }

        }

    }

}