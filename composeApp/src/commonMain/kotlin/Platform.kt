import org.koin.core.module.Module

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect val platformModules: Module

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ShareManager {
    fun shareText(shareContent: String)
}