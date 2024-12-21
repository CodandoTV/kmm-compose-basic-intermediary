import android.content.Context
import android.content.Intent
import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ShareManager(
    private val context: Context
) {
    actual fun shareText(shareContent: String) {
        // Cria um intent para compartilhar o conteúdo
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareContent)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        // Abre o share sheet
        context.startActivity(Intent.createChooser(intent, "Nome do App").apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }
}