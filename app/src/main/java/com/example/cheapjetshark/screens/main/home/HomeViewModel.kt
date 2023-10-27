package com.example.cheapjetshark.screens.main.home

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
class HomeViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val _newest20DealsList: MutableLiveData<DataOrException<DealsList, Boolean, Exception>> =
        MutableLiveData(
            DataOrException(null, true, Exception(""))
        )
    val newest20DealsList: LiveData<DataOrException<DealsList, Boolean, Exception>> =
        _newest20DealsList

    private val _topDealsList: MutableLiveData<DataOrException<DealsList, Boolean, Exception>> =
        MutableLiveData(
            DataOrException(null, true, Exception(""))
        )
    val topDealsList: LiveData<DataOrException<DealsList, Boolean, Exception>> = _topDealsList

    private val _topGamesDealsList: MutableLiveData<DataOrException<DealsList, Boolean, Exception>> =
        MutableLiveData(
            DataOrException(null, true, Exception(""))
        )
    val topGamesDealsList: LiveData<DataOrException<DealsList, Boolean, Exception>> =
        _topGamesDealsList


    fun getTopDealsList() {
        viewModelScope.launch {
            _topDealsList.value = getDeals(sortBy = "Deal Rating", pageSize = 20)
        }
    }

    fun getTopGamesDealsList() {
        viewModelScope.launch {
            _topGamesDealsList.value = getDeals(sortBy = "Reviews", pageSize = 20)
        }
    }

    fun getNewest20DealsList() {
        viewModelScope.launch {
            _newest20DealsList.value = getDeals(sortBy = "Recent", pageSize = 20)
        }
    }

    private suspend fun getDeals(
        storeID: Int? = null,
        upperPrice: Int? = null,
        lowerPrice: Int? = null,
        sortBy: String? = null,
        pageNumber: Int? = null,
        pageSize: Int? = null,
        title: String? = null
    ): DataOrException<DealsList, Boolean, Exception> {
        val dealsList = apiRepository.getListOfDeals(
            storeID = storeID,
            upperPrice = upperPrice,
            lowerPrice = lowerPrice,
            sortBy = sortBy,
            pageNumber = pageNumber,
            pageSize = pageSize,
            title = title
        )
        dealsList.loading = false
        Log.d("HomeViewModel", "getDeals: ${dealsList.loading}")
        return dealsList
    }


}