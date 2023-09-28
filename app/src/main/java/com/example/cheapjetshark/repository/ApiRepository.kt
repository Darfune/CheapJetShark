package com.example.cheapjetshark.repository

import android.util.Log
import com.example.cheapjetshark.data.Resource
import com.example.cheapjetshark.models.deal.Deal
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.models.gamesbyid.GamesById
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearch
import com.example.cheapjetshark.models.lastChange.StoresLastChange
import com.example.cheapjetshark.models.stores.Stores
import com.example.cheapjetshark.network.CheapSharkApi
import okhttp3.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: CheapSharkApi) {
    suspend fun getDealById(id: String): Resource<Deal> {
        val response = try {
            api.getDealById(id = id)
        } catch (exc: Exception) {
            Log.d("Api Repository", "getDealById: $exc")
            return Resource.Error("Unexpected Error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getListOfDeals(
        storeID: Int? = null,
        upperPrice: Int? = null,
        lowerPrice: Int? = null,
        sortBy: String? = null,
        pageNumber: Int? = null,
        title: String? = null
    ): Resource<DealsList> {
        val response = try {
            api.getListOfDeals(
                storeID = storeID,
                upperPrice = upperPrice,
                lowerPrice = lowerPrice,
                sortBy = sortBy,
                pageNumber = pageNumber,
                title = title
            )
        } catch (exc: Exception) {
            Log.d("Api Repository", "getListOfDeals: $exc")
            return Resource.Error("Unexpected Error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getGamesBySearch(title: String): Resource<GamesBySearch> {
        val response = try {
            api.getGamesBySearch(
                title = title
            )
        } catch (exc: Exception){
            Log.d("Api Repository", "getGamesBySearch: $exc")
            return Resource.Error("Unexpected Error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getGamesById(id: String): Resource<GamesById>{
        val response = try {
            api.getGamesById(
                id = id
            )
        } catch (exc: Exception){
            Log.d("Api Repository", "getGamesById: $exc")
            return Resource.Error("Unexpected Error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getStores(): Resource<Stores>{
        val response = try {
            api.getStores()
        } catch (exc: Exception){
            Log.d("Api Repository", "getStores: $exc")
            return Resource.Error("Unexpected Error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getLastChanges(): Resource<StoresLastChange>{
        val response = try {
            api.getLastChanges()
        } catch (exc: Exception){
            Log.d("Api Repository", "getLastChanges: $exc")
            return Resource.Error("Unexpected Error occurred.")
        }
        return Resource.Success(response)
    }

}