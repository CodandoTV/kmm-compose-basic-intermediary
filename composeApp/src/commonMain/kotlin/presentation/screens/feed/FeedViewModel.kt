package presentation.screens.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import presentation.screens.feed.data.FeedRepository
import presentation.screens.feed.data.FeedRepositoryImpl
import presentation.screens.feed.model.Post


data class FeedUiState(
    val posts: List<Post> = emptyList()
)

class FeedViewModel(
    private val feedRepository: FeedRepository = FeedRepositoryImpl()
) : ViewModel() {
    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState: StateFlow<FeedUiState>
        get() = _uiState

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(posts = feedRepository.getPosts())
        }
    }

    fun postPost(post: Post) {
        viewModelScope.launch {
            feedRepository.postPost(post)
            _uiState.value = _uiState.value.copy(posts = feedRepository.getPosts())
        }
    }
}