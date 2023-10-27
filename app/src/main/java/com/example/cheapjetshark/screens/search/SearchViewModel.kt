package com.example.cheapjetshark.screens.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapjetshark.data.DataOrException
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearch
import com.example.cheapjetshark.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val _searchTitle: MutableLiveData<DataOrException<GamesBySearch, Boolean, Exception>> =
        MutableLiveData(
            DataOrException(null, true, Exception(""))
        )
    val searchTitle: LiveData<DataOrException<GamesBySearch, Boolean, Exception>> = _searchTitle

    fun getSearchedTitleList(title: String){
        viewModelScope.launch {
            _searchTitle.value = getSearchedTitle(title = title)
        }
    }

    private suspend fun getSearchedTitle(title: String): DataOrException<GamesBySearch, Boolean, Exception> {
        val gamesList = apiRepository.getGamesBySearch(title = title)
        gamesList.loading = false
        Log.d("Searched", "getSearchedTitle: ${gamesList.data}")
        return gamesList
    }

}