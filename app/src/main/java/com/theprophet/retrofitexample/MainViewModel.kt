package com.theprophet.retrofitexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theprophet.retrofitexample.network.ApiClient
import com.theprophet.retrofitexample.network.Character
import com.theprophet.retrofitexample.network.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository = Repository(ApiClient.apiService)): ViewModel() {


    private var _charactersLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    val characterLiveData: LiveData<ScreenState<List<com.theprophet.retrofitexample.network.Character>?>>
        get() = _charactersLiveData


    init{
        fetchCharacter()

    }

    private fun fetchCharacter(){

        _charactersLiveData.postValue(ScreenState.Loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("MainViewModel", Thread.currentThread().name)
            try{
                val client = repository.getCharacters("1")
                _charactersLiveData.postValue(ScreenState.Success(client.result))

            }catch (e:Exception){
                _charactersLiveData.postValue(ScreenState.Error(e.message!!,null))
            }








        }

    }

}