package com.theprophet.retrofitexample

import com.theprophet.retrofitexample.network.ApiService

class Repository(private val apiService: ApiService) {
    suspend fun getCharacters(page: String) = apiService.fetchCharacters(page)
}