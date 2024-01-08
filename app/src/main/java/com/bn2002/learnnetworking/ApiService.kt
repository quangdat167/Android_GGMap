package com.bn2002.learnnetworking

import retrofit2.http.GET

interface ApiService {
    @GET("jsons/users.json")
    suspend fun getUsers(): List<UserData>
}