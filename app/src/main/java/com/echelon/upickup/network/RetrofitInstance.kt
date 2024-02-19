package com.echelon.upickup.network

import com.echelon.upickup.network.apiservice.SignInApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    //base url of our api
    private const val BASE_URL = "https://u-pick-up-y7qnw.ondigitalocean.app/api/"

    // add here kapag meron pang additional na lalagay
    private val retrofitSignIn: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val retrofitSignUp: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // dito naman, create kapag nagcreate doon sa taas, dapat partner sila
    val signInApiService: SignInApiService by lazy {
        retrofitSignIn.create(SignInApiService::class.java)
    }

}