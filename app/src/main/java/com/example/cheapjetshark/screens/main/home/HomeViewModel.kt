package com.example.cheapjetshark.screens.main.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheapjetshark.data.Resource
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {


    private val _newest20DealsList: MutableLiveData<Resource<DealsList>> = MutableLiveData()
    val newest20DealsList: MutableLiveData<Resource<DealsList>> =
        _newest20DealsList

    private val _topDealsList: MutableLiveData<Resource<DealsList>> = MutableLiveData()
    val topDealsList: MutableLiveData<Resource<DealsList>> =
        _topDealsList

    private var _topGamesDealsList: MutableLiveData<Resource<DealsList>> = MutableLiveData()
    val topGamesDealsList: MutableLiveData<Resource<DealsList>> =
        _topGamesDealsList


    init {
        loadDeals()


    }

    private fun loadDeals() {
        geDealsList(sortBy = "Recent", pageSize = 20,_newest20DealsList)
        geDealsList(sortBy = "Deal Rating", pageSize = 20,_topDealsList)
        geDealsList(sortBy = "Reviews", pageSize = 20,_topGamesDealsList)
    }

    private fun geDealsList(
        sortBy: String? = null,
        pageSize: Int? = null,
        list: MutableLiveData<Resource<DealsList>>,
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            try {
                when (val response = apiRepository.getListOfDeals(
                    sortBy = sortBy,
                    pageSize = pageSize
                )) {
                    is Resource.Success -> {
                        list.value = response
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