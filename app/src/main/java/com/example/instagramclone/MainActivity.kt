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
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.instagramclone.Data.Network.FeedRetrofitInstance
import com.example.instagramclone.Ui.Adapters.ViewpagerAdapter
import com.example.instagramclone.Ui.Fragments.FeedFragment
import com.example.instagramclone.Ui.Fragments.ReelFragment
import com.example.instagramclone.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

//    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewpagerAdapter: ViewpagerAdapter
    val Tag = "API_ERROR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setUpViewPager()
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

    private fun setUpViewPager() {
        //create list of fragments
        val listOfFragments = listOf(FeedFragment(), ReelFragment())

        // initialize adapter
        viewpagerAdapter = ViewpagerAdapter(
            listOfFragments,
            supportFragmentManager,
            lifecycle
        )

        //set the adapter onto viewpager
        binding.viewPager.adapter = viewpagerAdapter


        // attach tabLayout with viewpager and create tabs with text
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.icon = when(position){
                0 -> getDrawable(R.drawable.baseline_home_24)
                1 -> getDrawable(R.drawable.baseline_ad_units_24)
                else -> null
            }
        }.attach()
    }

//    override fun onSupportNavigateUp(): Boolean {
//        navController = findNavController(R.id.navHostfragmentContainerView)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}