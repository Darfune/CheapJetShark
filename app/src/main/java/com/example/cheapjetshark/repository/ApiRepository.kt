package com.example.cheapjetshark.repository

import android.util.Log
import com.example.cheapjetshark.data.DataOrException
import com.example.cheapjetshark.models.deal.Deal
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.models.gamesbyid.GamesById
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearch
import com.example.cheapjetshark.models.lastChange.StoresLastChange
import com.example.cheapjetshark.models.stores.Stores
import com.example.cheapjetshark.network.CheapSharkApi
import javax.inject.Inject

class ApiRepository @Inject constructor(private val api: CheapSharkApi) {
    suspend fun getDealById(id: String): DataOrException<Deal, Boolean, Exception> {
        val response = try {
            api.getDealById(id = id)
        } catch (exc: Exception) {
            Log.d("Api Repository", "getDealById: $exc")
            return DataOrException(e = exc)
        }
        return DataOrException(data = response)
    }

    suspend fun getListOfDeals(
        storeID: Int? = null,
        upperPrice: Int? = null,
        lowerPrice: Int? = null,
        sortBy: String? = null,
        pageNumber: Int? = null,
        title: String? = null
    ): DataOrException<DealsList, Boolean, Exception> {
        val response = try {
            api.getListOfDeals(
//                storeID = storeID,
//                upperPrice = upperPrice,
//                lowerPrice = lowerPrice,
//                sortBy = sortBy,
//                pageNumber = pageNumber,
//                title = title
            )
        } catch (exc: Exception) {
            Log.d("Api Repository", "getListOfDeals: $exc")
            return DataOrException(e = exc)
        }
        return DataOrException(data = response)
    }

    suspend fun getGamesBySearch(title: String): DataOrException<GamesBySearch, Boolean, Exception> {
        val response = try {
            api.getGamesBySearch(
                title = title
            )
        } catch (exc: Exception) {
            Log.d("Api Repository", "getGamesBySearch: $exc")
            return DataOrException(e = exc)
        }
        return DataOrException(data = response)
    }

    suspend fun getGamesById(id: String): DataOrException<GamesById, Boolean, Exception> {
        val response = try {
            api.getGamesById(
                id = id
            )
        } catch (exc: Exception) {
            Log.d("Api Repository", "getGamesById: $exc")
            return DataOrException(e = exc)
        }
        return DataOrException(data = response)
    }

    suspend fun getStores(): DataOrException<Stores, Boolean, Exception> {
        val response = try {
            api.getStores()
        } catch (exc: Exception) {
            Log.d("Api Repository", "getStores: $exc")
            return DataOrException(e = exc)
        }
        Log.d("Api Repository", "getStores: $response")
        return DataOrException(data = response)
    }

    suspend fun getLastChanges(): DataOrException<StoresLastChange, Boolean, Exception> {
        val response = try {
            api.getLastChanges()
        } catch (exc: Exception) {
            Log.d("Api Repository", "getLastChanges: $exc")
            return DataOrException(e = exc)
        }
        return DataOrException(data = response)
    }

}