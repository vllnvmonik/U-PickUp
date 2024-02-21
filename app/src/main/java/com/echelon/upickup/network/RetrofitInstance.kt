package com.echelon.upickup.network

import com.echelon.upickup.network.apiservice.ForgotPasswordApiService
import com.echelon.upickup.network.apiservice.LogoutApiService
import com.echelon.upickup.network.apiservice.SignInApiService
import com.echelon.upickup.network.apiservice.StudentDetailsApiService
import com.echelon.upickup.network.apiservice.PostApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    //base url of our api
    private const val BASE_URL = "https://u-pick-up-y7qnw.ondigitalocean.app/api/"

    // add here kapag meron pang additional na lalagay
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // dito naman, create kapag nagcreate doon sa taas, dapat partner sila
    val signInApiService: SignInApiService by lazy {
        retrofit.create(SignInApiService::class.java)
    }

    val studentDetailsApiService: StudentDetailsApiService by lazy {
        retrofit.create(StudentDetailsApiService::class.java)
    }
    val logoutApiService: LogoutApiService by lazy {
        retrofit.create(LogoutApiService::class.java)
    }
    val forgotPasswordApiService: ForgotPasswordApiService by lazy {
        retrofit.create(ForgotPasswordApiService::class.java)
    }

    val postApiService: PostApiService by lazy {
        retrofit.create(PostApiService::class.java)
    }
}