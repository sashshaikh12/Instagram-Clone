package com.example.instagramclone.Ui.Adapters

import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.Data.FeedDataClass
import com.example.instagramclone.R

class FeedAdapter(private val dataList: ArrayList<FeedDataClass>) : RecyclerView.Adapter<FeedAdapter.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.feed_item_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvAvatar.setImageResource(currentItem.avatar)
        holder.rvUsername.text = currentItem.username
        holder.rvPostImage.setImageResource(currentItem.postImage)
        holder.rvLikeButton.text = currentItem.likeButton
        holder.rvLikeCount.text = currentItem.likeCount
        holder.rvDescription.text = currentItem.description
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