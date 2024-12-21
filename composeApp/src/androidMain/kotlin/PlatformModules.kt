import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModules: Module = module {
    single { createDataStore(androidContext()) }
    single { ShareManager(androidContext()) }
}