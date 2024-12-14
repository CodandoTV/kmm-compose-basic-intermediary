package presentation.screens.postdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import presentation.screens.feed.data.FeedRepository
import presentation.screens.feed.data.FeedRepositoryImpl
import presentation.screens.feed.data.sampleComments
import presentation.screens.feed.data.sampleUsers
import presentation.screens.feed.model.Comment
import presentation.screens.feed.model.Post
import kotlin.random.Random


data class PostDetailUiState(
    val post: Post? = null,
    val comments: List<Comment> = emptyList()
)

class PostDetailViewModel(
    private val feedRepository: FeedRepository = FeedRepositoryImpl()
) : ViewModel() {
    private val _uiState = MutableStateFlow(PostDetailUiState())
    val uiState: StateFlow<PostDetailUiState>
        get() = _uiState

    fun loadPost(postId: String) {
        viewModelScope.launch {
            feedRepository.getPost(postId).let { post ->
                _uiState.value = _uiState.value.copy(
                    post = post,
                    comments = sampleComments
                )
            }
        }
    }

    fun addComment(comment: String) {
        viewModelScope.launch {
            val newComment = Comment(
                id = sampleUsers[0].hashCode().toString(),
                user = sampleUsers[0],
                content = comment
            )
            _uiState.value = _uiState.value.copy(
                comments = listOf(newComment) + _uiState.value.comments
            )
        }
    }
}