import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.dataStoreFileName
import data.getDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual val preferenceModule: Module = module {
    single { createDataStore() }
}


@OptIn(ExperimentalForeignApi::class)
fun createDataStore(): DataStore<Preferences> = getDataStore(
    producePath = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/$dataStoreFileName"
    }
)