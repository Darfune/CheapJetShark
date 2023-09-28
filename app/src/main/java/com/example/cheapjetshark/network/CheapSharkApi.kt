package com.example.cheapjetshark.network

import com.example.cheapjetshark.models.deal.Deal
import com.example.cheapjetshark.models.deals.DealsList
import com.example.cheapjetshark.models.gamesbyid.GamesById
import com.example.cheapjetshark.models.gamesbysearch.GamesBySearch
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
        @Query("title") title: String = ""
    ): DealsList

    @GET(value = "deals")
    suspend fun getDealById(
        @Query("id") id: String
    ): Deal

    @GET(value = "games")
    suspend fun getGamesBySearch(
        @Query("title") title: String
    ): GamesBySearch

    @GET(value = "games")
    suspend fun getGamesById(
        @Query("id") id: String
    ): GamesById

    @GET("store")
    suspend fun getStores(): Stores
}