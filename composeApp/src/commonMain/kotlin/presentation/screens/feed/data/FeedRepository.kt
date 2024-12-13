package presentation.screens.feed.data

import kotlinx.coroutines.flow.MutableStateFlow
import presentation.screens.feed.model.Post

interface FeedRepository {
    suspend fun postPost(post: Post)
    suspend fun getPosts(): List<Post>
    suspend fun getPost(postId: String): Post
}

class FeedRepositoryImpl(
    private val localDataSource: List<Post> = samplePosts,
) : FeedRepository {

    private val _posts = MutableStateFlow(localDataSource)

    override suspend fun postPost(post: Post) {
        val currentPosts = _posts.value + post
        _posts.value = currentPosts
    }

    override suspend fun getPosts(): List<Post> {
        return _posts.value
    }

    override suspend fun getPost(postId: String): Post {
        return _posts.value.first { it.id == postId }
    }
}