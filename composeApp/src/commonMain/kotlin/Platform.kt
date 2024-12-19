import org.koin.core.module.Module

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect class ShareManager {
    fun shareContent(content: String)
}

expect val preferenceModule: Module