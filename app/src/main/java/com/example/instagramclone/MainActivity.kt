package com.example.instagramclone

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.instagramclone.Data.Network.FeedRetrofitInstance
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    val Tag = "API_ERROR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            val response = try {
                FeedRetrofitInstance().api.getPosts()
            }catch (e: Exception){
                Log.e(Tag, "Error here = $e")
                return@launch
            }
            if(response.isSuccessful && response.body() != null)
            {
                val s = response.body().toString()
                Log.d(Tag, "Response = $s")
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.navHostfragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}