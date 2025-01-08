package presentation.screens.feed

import ShareManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.screens.feed.data.FeedRepository
import presentation.screens.feed.model.Post

data class FeedUiState(
    val posts: List<Post> = emptyList(),
)


class FeedViewModel(
    private val feedRepository: FeedRepository,
    private val shareManager: ShareManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            val posts = feedRepository.getPosts()
           _uiState.value = _uiState.value.copy(posts = posts)
        }
    }

    fun onSharePost(post: Post) {
        val contentPost = "Dá uma olhada no post: ${post.description}"
        shareManager.shareText(contentPost)
    }


}