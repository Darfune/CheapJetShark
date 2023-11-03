package com.example.cheapjetshark.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapjetshark.data.Resource
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearchItem
import com.example.cheapjetshark.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    var gamesFound: List<GamesBySearchItem> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)
    init {
        getSearchedTitle("")
    }

    fun getSearchedTitle(title: String) {
        isLoading = true
        viewModelScope.launch(Dispatchers.Default) {
            if (title.isEmpty()) return@launch
            try {
                when (val response = apiRepository.getGamesBySearch(title = title)) {
                    is Resource.Success -> {
                        gamesFound = response.data!!
                        if (gamesFound.isNotEmpty()) isLoading = false
                        Log.d("HomeViewModel", "getTheNewest20DealsList: ${response.data}")
                    }

                    is Resource.Error -> {
                        if (gamesFound.isNotEmpty()) isLoading = false
                        Log.d("HomeViewModel", "getTheNewest20DealsList: Failed getting deals")
                    }

                    else -> {if (gamesFound.isNotEmpty()) isLoading = false}
                }
            } catch (exception: Exception) {
                if (gamesFound.isNotEmpty()) isLoading = false
                Log.d("HomeViewModel", "getTheNewest20DealsList: ${exception.message.toString()}")
            }
        }
    }
}

