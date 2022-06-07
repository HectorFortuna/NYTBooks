package com.hectorfortuna.nytbooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hectorfortuna.nytbooks.data.model.Book
import com.hectorfortuna.nytbooks.data.response.APIService
import com.hectorfortuna.nytbooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks() {
        APIService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val books: MutableList<Book> = mutableListOf()

                    response.body()?.let { bookBodyResponse ->
                        for (result in bookBodyResponse.bookResults) {
                            val book = Book(
                                title = result.bookDetails[0].title,
                                author = result.bookDetails[0].author,
                                description = result.bookDetails[0].description,
                                publisher = result.bookDetails[0].publisher
                            )

                            books.add(book)
                        }
                    }
                    booksLiveData.value = books
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {

            }
        })
    }

}