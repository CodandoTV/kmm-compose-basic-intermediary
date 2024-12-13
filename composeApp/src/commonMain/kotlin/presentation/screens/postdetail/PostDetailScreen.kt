package presentation.screens.postdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import presentation.NavigationRoutes.PostIdArgument
import presentation.screens.feed.ItemPost
import presentation.screens.feed.model.Comment

@Composable
fun PostDetailScreen(navController: NavController) {
    val viewModel = remember { PostDetailViewModel() }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        val postId = navController.currentBackStackEntry?.arguments?.getString(PostIdArgument)
        postId?.let { viewModel.loadPost(it) }
    }

    PostDetailContent(uiState)
}

@Composable
fun PostDetailContent(
    uiState: PostDetailUiState
) {
    uiState.post?.let { post ->
        Column {
            ItemPost(post)
            Divider()
            CommentSection(uiState.comments)
        }
    }
}

@Composable
fun CommentSection(comments: List<Comment>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(comments) { comment ->
            ItemComment(comment)
        }
    }
}

@Composable
fun ItemComment(comment: Comment) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            modifier = Modifier.size(40.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            AsyncImage(
                comment.user.avatar,
                contentDescription = "Post image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = comment.user.username,
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = comment.content,
                style = MaterialTheme.typography.body1
            )
        }
    }
}