package presentation.screens.feed.model

data class Comment(
    val id: String,
    val user: User,
    val content: String
)