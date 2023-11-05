package com.example.cheapjetshark.repository

import android.util.Log
import com.example.cheapjetshark.data.Resource
import com.example.cheapjetshark.models.deal.Deal
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.models.gamesbyid.GameById
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearchItem
import com.example.cheapjetshark.models.lastChange.StoresLastChange
import com.example.cheapjetshark.network.CheapSharkApi
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: CheapSharkApi) {
    suspend fun getDealById(id: String): Resource<Deal> {
        val response = try {
            Resource.Loading(data = true)
            api.getDealById(id = id)
        } catch (exc: Exception) {
            Log.d("Api Repository", "getDealById: $exc")
            return Resource.Error(message = exc.toString())
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }

    suspend fun getListOfDeals(
        storeID: Int? = null,
        upperPrice: Int? = null,
        lowerPrice: Int? = null,
        sortBy: String? = null,
        pageNumber: Int? = null,
        pageSize: Int? = null,
        title: String? = null
    ): Resource<DealsList> {
        val response =  try {
            Resource.Loading(true)
            api.getListOfDeals(
                storeID = storeID,
                upperPrice = upperPrice,
                lowerPrice = lowerPrice,
                sortBy = sortBy,
                pageNumber = pageNumber,
                pageSize = pageSize,
                title = title
            )

        } catch (exc: Exception) {
            Log.d("Api Repository", "getListOfDeals: $exc")
            return Resource.Error(message = exc.toString())
        }
        Resource.Loading(false)
        return Resource.Success(data = response)
    }

    suspend fun getGamesBySearch(title: String): Resource<List<GamesBySearchItem>> {
        val response = try {
            Resource.Loading(true)
            api.getGamesBySearch(
                title = title
            )
        } catch (exc: Exception) {
            Log.d("Api Repository", "getGamesBySearch: $exc")
            return Resource.Error(message = exc.toString())
        }
        Resource.Loading(false)
        return Resource.Success(data = response)
    }

    suspend fun getGamesById(id: Int): Resource<GameById> {
        val response = try {
            Resource.Loading(true)
            api.getGamesById(
                id = id
            )
        } catch (exc: Exception) {
            Log.d("Api Repository", "getGamesById: $exc")
            return Resource.Error(message = exc.toString())
        }
        Resource.Loading(false)
        return Resource.Success(data = response)
    }

    suspend fun getLastChanges(): Resource<StoresLastChange> {
        val response = try {
            Resource.Loading(true)
            api.getLastChanges()
        } catch (exc: Exception) {
            Log.d("Api Repository", "getLastChanges: $exc")
            return Resource.Error(message = exc.toString())
        }
        Resource.Loading(false)
        return Resource.Success(data = response)
    }

}