package com.example.instagramclone.Ui.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.Data.FeedDataClass
import com.example.instagramclone.R
import com.example.instagramclone.Ui.Adapters.FeedAdapter
import com.example.instagramclone.databinding.FeedFragmentBinding

class FeedFragment : Fragment(R.layout.feed_fragment){

    private var _binding: FeedFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<FeedDataClass>
    lateinit var pfps : Array<Int>
    lateinit var usernames: Array<String>
    lateinit var postImages: Array<Int>
    lateinit var likeButtons: Array<String>
    lateinit var likeCounts: Array<String>
    lateinit var desc: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FeedFragmentBinding.bind(view)
        pfps = arrayOf(
            R.drawable.instagram_logo,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground
        )
        usernames = arrayOf("user1", "user2", "user3")
        postImages = arrayOf(
            R.drawable.instagram_logo,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground
        )
        likeButtons = arrayOf("Like", "Like", "Like")
        likeCounts = arrayOf("50", "100", "2030")
        desc = arrayOf("by user1", "by user2", "by user3")

        recyclerView = binding.FeedRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<FeedDataClass>()
        getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getData(){
        for(i in 0 until pfps.size){
            val dataClass = FeedDataClass(pfps[i], usernames[i], postImages[i], likeButtons[i], likeCounts[i], desc[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = FeedAdapter(dataList)
    }
}