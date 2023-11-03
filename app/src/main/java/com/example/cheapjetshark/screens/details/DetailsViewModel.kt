package com.example.cheapjetshark.screens.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapjetshark.data.Resource
import com.example.cheapjetshark.models.gamesbyid.GameById
import com.example.cheapjetshark.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private  val apiRepository: ApiRepository): ViewModel() {

    var gameDetails: GameById by mutableStateOf(GameById(null,null, null))
    var isLoading: Boolean by mutableStateOf(true)

    init {
        getGameDetails(1)
    }

    private fun getGameDetails(gameID: Int) {
        isLoading = true
        viewModelScope.launch(Dispatchers.Default) {
            if (gameID == null) return@launch
            try {
                when (val response = apiRepository.getGamesById(gameID)){
                    is Resource.Success -> {
                        gameDetails = response.data!!
                        if (gameDetails.equals(null)) isLoading = false
                        Log.d("HomeViewModel", "getTheNewest20DealsList: ${response.data}")
                    }

                    is Resource.Error -> {
                        if (gameDetails.equals(null)) isLoading = false
                        Log.d("HomeViewModel", "getTheNewest20DealsList: Failed getting deals")
                    }

                    else -> {if (gameDetails.equals(null)) isLoading = false}
                }
            } catch (exception: Exception) {
                if (gameDetails.equals(null)) isLoading = false
                Log.d("HomeViewModel", "getTheNewest20DealsList: ${exception.message.toString()}")
            }
        }
    }

}