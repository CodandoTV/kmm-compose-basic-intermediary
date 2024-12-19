
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()


actual class ShareManager {
    actual fun shareContent(content: String) {
        // Cria um array com os itens a serem compartilhados
        val itemsToShare = listOf(content)

        // Cria o UIActivityViewController com os itens
        val activityViewController = UIActivityViewController(itemsToShare, null)

        // Obtém o UIViewController atual para apresentar o activityViewController
        val currentViewController =
            UIApplication.sharedApplication.keyWindow?.rootViewController ?: return

        // Apresenta o activityViewController no thread principal
        currentViewController.presentViewController(
            activityViewController,
            animated = true,
            completion = null
        )
    }
}