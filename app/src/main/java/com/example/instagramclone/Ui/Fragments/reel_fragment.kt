package com.example.instagramclone.Ui.Fragments

import FeedRepository
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.instagramclone.Data.Local.Database.AppDatabase
import com.example.instagramclone.Data.Network.FeedRetrofitInstance
import com.example.instagramclone.Data.Network.ReelRetrofitInstance
import com.example.instagramclone.Data.Repository.ReelRepository
import com.example.instagramclone.R
import com.example.instagramclone.Ui.Adapters.FeedAdapter
import com.example.instagramclone.Ui.Adapters.ReelAdapter
import com.example.instagramclone.Ui.State.FeedUiState
import com.example.instagramclone.Ui.State.ReelUiState
import com.example.instagramclone.Viewmodel.FeedViewModel
import com.example.instagramclone.Viewmodel.ReelViewModel
import com.example.instagramclone.databinding.FeedFragmentBinding
import com.example.instagramclone.databinding.ReelFragmentBinding
import com.google.android.material.snackbar.Snackbar

class ReelFragment : Fragment(R.layout.reel_fragment)
{
    private var _binding: ReelFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var reelViewModel: ReelViewModel
    private lateinit var reelAdapter: ReelAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = ReelFragmentBinding.bind(view)

        reelAdapter = ReelAdapter { reel ->
            reelViewModel.onLikeClicked(reel)
        }

        _binding!!.viewPager.adapter = reelAdapter
        _binding!!.viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL


        val dao = AppDatabase.getInstance(requireContext()).reelDao()
        val api = ReelRetrofitInstance().api
        val repository = ReelRepository(api, dao, requireContext())
        reelViewModel = ReelViewModel(repository)

        reelViewModel.reelState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ReelUiState.Success -> {
                    Log.d("REELS", "$state.reels")
                    reelAdapter.submitList(state.reels)

                    if (state.isOffline) {
                        Toast.makeText(
                            requireContext(),
                            "No Network Connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is ReelUiState.Error -> {
                    Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                }
                else -> Unit
            }
        }

    }

    override fun onPause() {
        super.onPause()
        reelAdapter.pauseAllPlayers()
    }

    override fun onResume() {
        super.onResume()
        reelAdapter.resumeVisiblePlayers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        reelAdapter.releaseAllPlayers()
        _binding = null
    }

}