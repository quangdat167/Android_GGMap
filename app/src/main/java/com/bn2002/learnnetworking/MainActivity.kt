package com.bn2002.learnnetworking

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = UserAdapter(emptyList()) { user ->
            openGoogleMap(this, user.address.geo.lat, user.address.geo.lng)
        }
        recyclerView.adapter = adapter

        // Kết nối và lấy dữ liệu từ API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://lebavui.github.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val users = apiService.getUsers()
                adapter.updateData(users)
            } catch (e: Exception) {
                // Xử lý lỗi
                Toast.makeText(this@MainActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

private fun openGoogleMap(context: Context, lat: Double, lng: Double) {
    val gmmIntentUri = Uri.parse("geo:$lat, $lng")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    } else {
        Toast.makeText(context, "Google Maps app is not installed", Toast.LENGTH_SHORT).show()
    }
}