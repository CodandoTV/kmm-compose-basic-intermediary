import androidx.compose.ui.graphics.ImageBitmap

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect class ShareManager {
    fun shareContent(content: String)
}
