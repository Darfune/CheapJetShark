package com.example.cheapjetshark.screens.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapjetshark.data.DataOrException
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val _newest20DealsList: MutableLiveData<DataOrException<DealsList, Boolean, Exception>> = MutableLiveData(
        DataOrException(null, true, Exception(""))
    )
    val newest20DealsList: LiveData<DataOrException<DealsList, Boolean, Exception>> = _newest20DealsList

    init {
        viewModelScope.launch { getListOfDeals(sortBy = "Recent", pageSize = 20) }
    }

    private suspend fun getListOfDeals(
        storeID: Int? = null,
        upperPrice: Int? = null,
        lowerPrice: Int? = null,
        sortBy: String? = null,
        pageNumber: Int? = null,
        pageSize: Int? = null,
        title: String? = null
    ): DataOrException<DealsList, Boolean, Exception> {
        _newest20DealsList.value = apiRepository.getListOfDeals(
            storeID = storeID,
            upperPrice = upperPrice,
            lowerPrice = lowerPrice,
            sortBy = sortBy,
            pageNumber = pageNumber,
            pageSize = pageSize,
            title = title
        )
        Log.d("MainViewModel", "getListOfDeals: ${_newest20DealsList.value!!.data!!.size}")
        return _newest20DealsList.value!!
    }

}