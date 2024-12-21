package presentation.screens.feed

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
    private val feedRepository: FeedRepository
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


}