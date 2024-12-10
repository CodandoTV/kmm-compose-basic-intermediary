package presentation.screens.feed.model

data class Post(
    val id: String,
    val description: String,
    val likes: Int,
    val comments: Int,
    val time: String,
    val user: User,
    val images: List<String>,
)

