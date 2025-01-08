import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModules: Module = module {
    single { createDataStore() }
    single { ShareManager() }
}