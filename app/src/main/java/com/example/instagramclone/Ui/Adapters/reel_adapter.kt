package com.example.instagramclone.Ui.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.Data.Local.Entity.ReelEntity
import com.example.instagramclone.R
import com.example.instagramclone.Ui.Adapters.FeedAdapter.ViewHolderClass

class ReelAdapter(private val onLikeClick: (ReelEntity) -> Unit) : RecyclerView.Adapter<ReelAdapter.ViewHolder>()
{

    private val dataList = ArrayList<ReelEntity>()
    private val activeHolders = mutableSetOf<ViewHolder>()

    fun submitList(newList: List<ReelEntity>) {
        dataList.clear()
        dataList.addAll(newList)
        Log.d("dataListReels", "$dataList")
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val playerView: PlayerView = itemView.findViewById(R.id.exoPlayer)
        val rvAvatar: ImageView = itemView.findViewById(R.id.profilePicReel)
        val rvUsername: TextView = itemView.findViewById(R.id.usernameReel)
        val rvLikeButton: Button = itemView.findViewById(R.id.likeButtonReel)
        val rvLikeCount: TextView = itemView.findViewById(R.id.likeCountReel)
        val rvDescription: TextView = itemView.findViewById(R.id.reelDescription)
        var player: ExoPlayer? = null
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

        val reel = dataList[position]

        holder.rvUsername.text = reel.user_name
        holder.rvLikeCount.text = reel.like_count.toString()
        holder.rvDescription.text = "by ${reel.user_name}"

        // Load images from URL (API → Room → UI)
        Glide.with(holder.itemView.context)
            .load(reel.user_image)
            .into(holder.rvAvatar)

        holder.rvLikeButton.text =
            if (reel.liked_by_user) "Liked" else "Like"

        holder.rvLikeButton.setOnClickListener {
            onLikeClick(reel)
        }

        holder.player?.release()
        holder.player = ExoPlayer.Builder(holder.itemView.context).build()
        holder.playerView.player = holder.player
        val mediaItem = MediaItem.fromUri(reel.reel_video)
        holder.player?.setMediaItem(mediaItem)
        holder.player?.prepare()
        holder.player?.playWhenReady = true
        activeHolders.add(holder)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.player?.pause()
    }

    // 🔥 Called when view is recycled
    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.player?.release()
        holder.player = null
        activeHolders.remove(holder)
    }

    // 🔥 Called when item becomes visible
    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.player?.play()
    }

    // 🔥 Called from Activity
    fun pauseAllPlayers() {
        for (holder in activeHolders) {
            holder.player?.pause()
        }
    }

    fun resumeVisiblePlayers() {
        for (holder in activeHolders) {
            holder.player?.play()
        }
    }

    fun releaseAllPlayers() {
        for (holder in activeHolders) {
            holder.player?.release()
        }
        activeHolders.clear()
    }

}