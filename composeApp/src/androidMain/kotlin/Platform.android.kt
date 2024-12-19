import android.content.Context
import android.content.Intent
import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()


actual class ShareManager(
    private val context: Context
) {
    actual fun shareContent(content: String) {

        // Cria um intent para compartilhar o conteúdo
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, content)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Necessário para contexts não-Activity
        }

        // Abre o share sheet
        context.startActivity(Intent.createChooser(intent, "Confira o post:").apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }
}