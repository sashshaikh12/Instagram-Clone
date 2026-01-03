package com.example.instagramclone.Ui.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.R

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.ViewHolderClass>() {

    private val dataList = ArrayList<FeedEntity>()

    fun submitList(newList: List<FeedEntity>) {
        dataList.clear()
        dataList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_item_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]

        holder.rvUsername.text = currentItem.user_name
        holder.rvLikeCount.text = currentItem.like_count.toString()
        holder.rvDescription.text = "by ${currentItem.user_name}"

        // Load images from URL (API → Room → UI)
        Glide.with(holder.itemView.context)
            .load(currentItem.user_image)
            .into(holder.rvAvatar)

        Glide.with(holder.itemView.context)
            .load(currentItem.post_image)
            .into(holder.rvPostImage)

        holder.rvLikeButton.text =
            if (currentItem.liked_by_user) "Liked" else "Like"
    }

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvAvatar: ImageView = itemView.findViewById(R.id.profilePic)
        val rvUsername: TextView = itemView.findViewById(R.id.username)
        val rvPostImage: ImageView = itemView.findViewById(R.id.postImage)
        val rvLikeButton: Button = itemView.findViewById(R.id.likeButton)
        val rvLikeCount: TextView = itemView.findViewById(R.id.likeCount)
        val rvDescription: TextView = itemView.findViewById(R.id.postDescription)
    }
}
