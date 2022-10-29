package com.dominikwannemaker.sportquiz.freo.data.datasource

import com.dominikwannemaker.sportquiz.freo.utils.Constants
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("/sportquiz")
    suspend fun testConnection(
        @Query("advertising_id") advertisingId: String? = null,
        @Query("appsflyer_id") appsflyerId: String? = null,
        @Query("campaign_id") campaignId: String? = null,
        @Query("campaign_name") campaignName: String? = null,
        @Query("af_channel") afChannel: String? = null
    ): Response<Unit>

}