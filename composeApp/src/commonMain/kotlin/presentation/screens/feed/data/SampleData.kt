package presentation.screens.feed.data

import presentation.screens.feed.model.Comment
import presentation.screens.feed.model.Post
import presentation.screens.feed.model.User

val sampleUsers = listOf(
    User(
        id = "5",
        name = "Pedro Lima",
        username = "pedrolima",
        avatar = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_6.png"
    ),
    User(
        id = "3",
        name = "Carlos Souza",
        username = "carlossouza",
        avatar = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_3.png"
    ),
    User(
        id = "4",
        name = "Ana Pereira",
        username = "anapereira",
        avatar = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_5.png"
    ),
    User(
        id = "1",
        name = "João Silva",
        username = "joaosilva",
        avatar = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_1.png"
    ),
    User(
        id = "2",
        name = "Maria Oliveira",
        username = "mariaoliveira",
        avatar = "https://raw.githubusercontent.com/git-jr/sample-files/main/profile%20pics/netflix_profile_pic_2.png"
    )
)

val sampleImages = listOf(
    "https://raw.githubusercontent.com/git-jr/sample-files/refs/heads/main/images/ai-generate/cachorro-grama.webp",
    "https://raw.githubusercontent.com/git-jr/sample-files/refs/heads/main/images/ai-generate/cerejeira-neon-1.webp",
    "https://raw.githubusercontent.com/git-jr/sample-files/refs/heads/main/images/ai-generate/cerejeira-neon-2.webp",
    "https://raw.githubusercontent.com/git-jr/sample-files/refs/heads/main/images/ai-generate/londres-neon.webp",
    "https://raw.githubusercontent.com/git-jr/sample-files/refs/heads/main/images/ai-generate/mesa-cafe-notebook.webp",
    "https://raw.githubusercontent.com/git-jr/sample-files/refs/heads/main/images/ai-generate/sushi.webp",
    "https://raw.githubusercontent.com/git-jr/sample-files/refs/heads/main/images/ai-generate/yoga-parque.webp",
)

val samplePosts = listOf(
    Post(
        id = "1",
        description = "Post description 1",
        likes = 10,
        comments = 2,
        time = "2023-10-01T10:00:00Z",
        user = sampleUsers[0],
        images = sampleImages.take(2)
    ),
    Post(
        id = "2",
        description = "Post description 2",
        likes = 20,
        comments = 4,
        time = "2023-10-02T11:00:00Z",
        user = sampleUsers[2],
        images = sampleImages.takeLast(2)
    ),
    Post(
        id = "3",
        description = "Post description 3",
        likes = 30,
        comments = 6,
        time = "2023-10-03T12:00:00Z",
        user = sampleUsers[1],
        images = sampleImages.take(2).reversed()
    ),
    Post(
        id = "4",
        description = "Post description 4",
        likes = 40,
        comments = 8,
        time = "2023-10-04T13:00:00Z",
        user = sampleUsers[3],
        images = sampleImages.takeLast(3)
    ),
    Post(
        id = "5",
        description = "Post description 5",
        likes = 50,
        comments = 10,
        time = "2023-10-05T14:00:00Z",
        user = sampleUsers[4],
        images = sampleImages.takeLast(4)
    )
)

val sampleComments = listOf(
    Comment(
        id = "1",
        user = sampleUsers[4],
        content = "Isso melhorou muito meu dia!",
    ),
    Comment(
        id = "2",
        user = sampleUsers[1],
        content = "kkkkkkkk, realmente!",
    ),
    Comment(
        id = "3",
        user = sampleUsers[2],
        content = "Com certeza!"
    ),
    Comment(
        id = "4",
        user = sampleUsers[3],
        content = "Muito bom!"
    ),
    Comment(
        id = "5",
        user = sampleUsers[0],
        content = "Adorei!"
    )
)

