package com.example.cheapjetshark.screens.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cheapjetshark.data.DataOrException
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    val dealsList: MutableState<DataOrException<DealsList, Boolean, Exception>> = mutableStateOf(
        DataOrException(null, true, Exception(""))
    )

    suspend fun getListOfDeals(
        storeID: Int? = null,
        upperPrice: Int? = null,
        lowerPrice: Int? = null,
        sortBy: String? = null,
        pageNumber: Int? = null,
        title: String? = null
    ): DataOrException<DealsList, Boolean, Exception> {
        return apiRepository.getListOfDeals(
            storeID = storeID,
            upperPrice = upperPrice,
            lowerPrice = lowerPrice,
            sortBy = sortBy,
            pageNumber = pageNumber,
            title = title
        )
    }

}