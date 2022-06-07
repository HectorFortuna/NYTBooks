package com.hectorfortuna.nytbooks.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hectorfortuna.nytbooks.databinding.ActivityBooksDetailsBinding

class BookDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBooksDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bookDetailsTitle.text = intent.getStringExtra(EXTRA_TITLE)
        binding.bookDetailsDescrition.text = intent.getStringExtra(EXTRA_DESCRIPTION)
        binding.bookDetailsPublisher.text = intent.getStringExtra(EXTRA_PUBLISHER)

    }

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        private const val EXTRA_PUBLISHER = "EXTRA_PUBLISHER"

        fun getStartIntent(context: Context, title: String, description: String, publisher : String) : Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE,title)
                putExtra(EXTRA_DESCRIPTION,description)
                putExtra(EXTRA_PUBLISHER,publisher)
            }
        }
    }
}