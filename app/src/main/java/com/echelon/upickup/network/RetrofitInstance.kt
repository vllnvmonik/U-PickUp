package com.echelon.upickup.network

import com.echelon.upickup.network.apiservice.CalendarApiService
import com.echelon.upickup.network.apiservice.FilterBooksApiService
import com.echelon.upickup.network.apiservice.FilterModulesApiService
import com.echelon.upickup.network.apiservice.FilterUniformApiService
import com.echelon.upickup.network.apiservice.ForgotPasswordApiService
import com.echelon.upickup.network.apiservice.InventoryBooksApiService
import com.echelon.upickup.network.apiservice.InventoryModulesApiService
import com.echelon.upickup.network.apiservice.InventoryUniformsApiService
import com.echelon.upickup.network.apiservice.LogoutApiService
import com.echelon.upickup.network.apiservice.SignInApiService
import com.echelon.upickup.network.apiservice.StudentDetailsApiService
import com.echelon.upickup.network.apiservice.PostApiService
import com.echelon.upickup.network.apiservice.SignUpApiService
import com.echelon.upickup.network.apiservice.CheckStudentEmail
import com.echelon.upickup.network.apiservice.CheckStudentID
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

    val signUpApiService: SignUpApiService by lazy {
        retrofit.create(SignUpApiService::class.java)
    }

    val checkStudentID: CheckStudentID by lazy {
        retrofit.create(CheckStudentID::class.java)
    }

    val checkStudentEmail: CheckStudentEmail by lazy {
        retrofit.create(CheckStudentEmail::class.java)
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
    val calendarApiService: CalendarApiService by lazy {
        retrofit.create(CalendarApiService::class.java)
    }
    // inventory items
    val inventoryBooksApiService: InventoryBooksApiService by lazy {
        retrofit.create(InventoryBooksApiService::class.java)
    }
    val filterBooksApiService: FilterBooksApiService by lazy {
        retrofit.create(FilterBooksApiService::class.java)
    }

    val inventoryModulesApiService: InventoryModulesApiService by lazy {
        retrofit.create(InventoryModulesApiService::class.java)
    }
    val filterModulesApiService: FilterModulesApiService by lazy {
        retrofit.create(FilterModulesApiService::class.java)
    }

    val inventoryUniformsApiService: InventoryUniformsApiService by lazy {
        retrofit.create(InventoryUniformsApiService::class.java)
    }
    val filterUniformApiService: FilterUniformApiService by lazy {
        retrofit.create(FilterUniformApiService::class.java)
    }
}