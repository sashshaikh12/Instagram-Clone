package com.example.instagramclone.Ui.Fragments

import FeedRepository
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagramclone.Data.Local.Database.AppDatabase
import com.example.instagramclone.Data.Network.FeedRetrofitInstance
import com.example.instagramclone.R
import com.example.instagramclone.Ui.Adapters.FeedAdapter
import com.example.instagramclone.Ui.State.FeedUiState
import com.example.instagramclone.Viewmodel.FeedViewModel
import com.example.instagramclone.databinding.FeedFragmentBinding

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private var _binding: FeedFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var feedViewModel: FeedViewModel
    private lateinit var feedAdapter: FeedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FeedFragmentBinding.bind(view)

        feedAdapter = FeedAdapter()
        binding.FeedRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = feedAdapter
            setHasFixedSize(true)
        }

        val dao = AppDatabase.getInstance(requireContext()).feedDao()
        val api = FeedRetrofitInstance().api
        val repository = FeedRepository(api, dao, requireContext())
        feedViewModel = FeedViewModel(repository)

        feedViewModel.feedState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is FeedUiState.Loading -> {
                    // show progress bar if you want
                }
                is FeedUiState.Success -> {
                    feedAdapter.submitList(state.posts)
                }
                is FeedUiState.Error -> {
                    // show snackbar
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
