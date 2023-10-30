package com.example.cheapjetshark.screens.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapjetshark.data.Resource
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearch
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearchItem
import com.example.cheapjetshark.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    var searchTitle: List<GamesBySearchItem> by mutableStateOf(listOf())
    init {
        getSearchedTitle("")
    }

    fun getSearchedTitle(title: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (title.isEmpty()) return@launch
            try {
                when (val response = apiRepository.getGamesBySearch(title = title)) {
                    is Resource.Success -> {
                        searchTitle = response.data!!
                        Log.d("HomeViewModel", "getTheNewest20DealsList: ${response.data}")
                    }

                    is Resource.Error -> {
                        Log.d("HomeViewModel", "getTheNewest20DealsList: Failed getting deals")
                    }

                    else -> {}
                }
            } catch (exception: Exception) {
                Log.d("HomeViewModel", "getTheNewest20DealsList: ${exception.message.toString()}")
            }
        }
    }
}

