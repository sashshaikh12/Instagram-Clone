import android.Manifest
import android.content.Context
import android.util.Log
import androidx.annotation.RequiresPermission
import com.example.instagramclone.Data.Local.Dao.FeedDao
import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.Data.Network.FeedApiService
import com.example.instagramclone.Ui.FeedResult
import com.example.instagramclone.Utils.NetworkUtils

class FeedRepository(
    private val api: FeedApiService,
    private val dao: FeedDao,
    private val context: Context
) {

    val TAG = "ERROR"
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    suspend fun getFeed(): FeedResult {

        val isOnline = NetworkUtils.isNetworkAvailable(context)
        if (isOnline) {
            try {
                val response = api.getPosts()

                // Map DTO → Entity
                val dtoList = response.body()?.posts ?: emptyList()


                val entities = dtoList.map { dto ->
                    FeedEntity(
                        post_id = dto.post_id,
                        user_name = dto.user_name,
                        user_image = dto.user_image,
                        post_image = dto.post_image,
                        like_count = dto.like_count,
                        liked_by_user = dto.liked_by_user
                    )
                }

                // Save to Room
                dao.insertPosts(entities)

            } catch (e: Exception) {
                Log.d(TAG, "error = $e")
            }
        }

        // Always return Room data as it is the source of truth
        return FeedResult(
            posts = dao.getAllPosts(),
            isOffline = !isOnline
        )
    }
}
