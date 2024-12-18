import di.appModules
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin{
        modules(appModules)
    }
}