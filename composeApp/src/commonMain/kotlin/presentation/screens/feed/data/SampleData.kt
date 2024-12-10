package presentation.screens.feed.data

import presentation.screens.feed.model.Post
import presentation.screens.feed.model.User


val samplePosts = listOf(
    Post(
        id = "1",
        description = "Post description 1",
        likes = 10,
        comments = 2,
        time = "2023-10-01T10:00:00Z",
        user = User(id = "1", name = "João Silva", username = "joaosilva", avatar = "avatar1.png"),
        images = listOf("image1.png", "image2.png")
    ),
    Post(
        id = "2",
        description = "Post description 2",
        likes = 20,
        comments = 4,
        time = "2023-10-02T11:00:00Z",
        user = User(id = "2", name = "Maria Oliveira", username = "mariaoliveira", avatar = "avatar2.png"),
        images = listOf("image3.png", "image4.png")
    ),
    Post(
        id = "3",
        description = "Post description 3",
        likes = 30,
        comments = 6,
        time = "2023-10-03T12:00:00Z",
        user = User(id = "3", name = "Carlos Souza", username = "carlossouza", avatar = "avatar3.png"),
        images = listOf("image5.png", "image6.png")
    ),
    Post(
        id = "4",
        description = "Post description 4",
        likes = 40,
        comments = 8,
        time = "2023-10-04T13:00:00Z",
        user = User(id = "4", name = "Ana Pereira", username = "anapereira", avatar = "avatar4.png"),
        images = listOf("image7.png", "image8.png")
    ),
    Post(
        id = "5",
        description = "Post description 5",
        likes = 50,
        comments = 10,
        time = "2023-10-05T14:00:00Z",
        user = User(id = "5", name = "Pedro Lima", username = "pedrolima", avatar = "avatar5.png"),
        images = listOf("image9.png", "image10.png")
    )
)