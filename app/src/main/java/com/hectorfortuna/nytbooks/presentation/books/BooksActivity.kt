package com.hectorfortuna.nytbooks.presentation.books


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hectorfortuna.nytbooks.R
import com.hectorfortuna.nytbooks.databinding.ActivityBooksBinding
import com.hectorfortuna.nytbooks.presentation.details.BookDetailsActivity

class BooksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBooksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.toolbarMain.title = getString(R.string.books_title)
        setSupportActionBar(binding.toolbarMain)

        val viewModel: BooksViewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(binding.recyclerBooks) {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(
                            this@BooksActivity,
                            book.title,
                            book.description,
                            book.publisher
                        )
                        this@BooksActivity.startActivity(intent)

                    }
                }
            }
        })

        viewModel.getBooks()

    }
}
