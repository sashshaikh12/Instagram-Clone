package com.example.instagramclone.Ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.Data.Local.Entity.ReelEntity
import com.example.instagramclone.R
import com.example.instagramclone.Ui.Adapters.FeedAdapter.ViewHolderClass

class ReelAdapter(private val onLikeClick: (ReelEntity) -> Unit) : RecyclerView.Adapter<ReelAdapter.ViewHolder>()
{

    private val dataList = ArrayList<ReelEntity>()

    fun submitList(newList: List<ReelEntity>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val rvExoPlayer : ExoPlayer = itemView.findViewById(R.id.exoPlayer)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.reel_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}