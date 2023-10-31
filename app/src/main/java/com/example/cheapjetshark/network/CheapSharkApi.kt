package com.example.cheapjetshark.network

import com.example.cheapjetshark.models.deal.Deal
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.models.gamesbyid.GameById
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearchItem
import com.example.cheapjetshark.models.lastChange.StoresLastChange
import com.example.cheapjetshark.models.stores.Stores
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CheapSharkApi {

    @GET(value = "deals")
    suspend fun getListOfDeals(
        @Query("storeID") storeID: Int? = null,
        @Query("upperPrice") upperPrice: Int? = null,
        @Query("lowerPrice") lowerPrice: Int? = null,
        @Query("sortBy") sortBy: String? = null,
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("title") title: String? = null,
        @Query("onSale") onSale: Boolean? = null
    ): DealsList

    @GET(value = "deals")
    suspend fun getDealById(
        @Query("id") id: String
    ): Deal

    @GET(value = "games")
    suspend fun getGamesBySearch(
        @Query("title") title: String
    ): List<GamesBySearchItem>

    @GET(value = "games")
    suspend fun getGamesById(
        @Query("id") id: String
    ): GameById

    @GET("stores")
    suspend fun getStores(): Stores

    @GET("stores?lastChange=")
    suspend fun getLastChanges(): StoresLastChange
}