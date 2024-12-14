package presentation.screens.postdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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

    PostDetailContent(
        uiState,
        onAddComment = { comment ->
            viewModel.addComment(comment)
        }
    )
}

@Composable
fun PostDetailContent(
    uiState: PostDetailUiState,
    onAddComment: (String) -> Unit
) {
    uiState.post?.let { post ->
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                ItemPost(post)
                Divider()
                CommentSection(uiState.comments)
            }

            Row(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .heightIn(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var commentEditText by remember { mutableStateOf("") }
                val keyboardController = LocalSoftwareKeyboardController.current

                OutlinedTextField(
                    value = commentEditText,
                    onValueChange = { commentEditText = it },
                    label = { Text("Add a comment") },
                    modifier = Modifier
                        .weight(0.9f),
                )

                Box(
                    modifier = Modifier
                        .weight(0.1f),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            onAddComment(commentEditText)
                            keyboardController?.hide()
                            commentEditText = ""
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Send",
                            tint = Color.Gray,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CommentSection(comments: List<Comment>) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            items(comments) { comment ->
                ItemComment(comment)
            }
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