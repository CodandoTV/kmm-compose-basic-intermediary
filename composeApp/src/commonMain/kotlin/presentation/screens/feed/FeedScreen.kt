@file:OptIn(ExperimentalMaterialApi::class)

package presentation.screens.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import presentation.screens.feed.model.Post

@Composable
fun FeedScreen(navController: NavController) {
    val viewModel = remember { FeedViewModel() }
    val uiState by viewModel.uiState.collectAsState()

    FeedScreenContent(uiState)
}

@Composable
fun FeedScreenContent(
    uiState: FeedUiState
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(uiState.posts) { post ->
            ItemPost(post)
        }
    }
}

@Composable
fun ItemPost(post: Post) {
    PostHeader(post = post)
    Spacer(modifier = Modifier.height(8.dp))
    if (post.images.size > 1) {
        PostMedia(images = post.images)
    }
    PostActions(post = post)
}

@Composable
private fun PostHeader(post: Post) {
    ListItem(
        text = {
            Text(
                text = post.user.name,
                fontWeight = FontWeight.Medium,
            )
        },
        secondaryText = {
            Text(
                text = post.user.username,
                style = MaterialTheme.typography.body2,
            )
        },
        trailing = {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = "More options",
            )
        },
        icon = {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            ) {
                AsyncImage(
                    post.user.avatar,
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                )
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PostMedia(images: List<String>) {

    val pagerState = rememberPagerState(pageCount = {
        images.size
    })

    HorizontalPager(state = pagerState) { page ->
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            AsyncImage(
                images[page],
                contentDescription = "Post image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                Text(
                    text = "$page / ${images.size}",
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(12.dp,8.dp),
                )
            }
        }
    }
}


@Composable
private fun PostActions(post: Post) {
    Row(
        Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                imageVector = Icons.Outlined.Favorite,
                contentDescription = "Like",
                modifier = Modifier.size(24.dp),
            )
            Text(
                text = post.likes.toString(),
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row {
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = "Comment",
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = "Share",
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer {
                        rotationY = 180f
                    },
            )
        }
    }

    Row {
        val nameWithDescription = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(post.user.name)
            }
            append(" ")
            append(post.description)
        }

        Text(
            text = nameWithDescription,
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 14.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
        )
    }

    Text(
        text = "Há 6 horas",
        modifier = Modifier.padding(start = 16.dp),
        fontSize = 12.sp,
    )
    Spacer(modifier = Modifier.height(8.dp))
}