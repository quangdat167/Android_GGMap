package com.bn2002.learnnetworking

data class UserData(
    val name: String,
    val email: String,
    val address: Address,
    val avatar: Avatar,
)

data class Address(
    val street: String,
    val city: String,
    val geo: Geo,
)

data class Avatar(
    val thumbnail: String,
    val photo: String
)

data class Geo(
    val lat: Double,
    val lng: Double
)