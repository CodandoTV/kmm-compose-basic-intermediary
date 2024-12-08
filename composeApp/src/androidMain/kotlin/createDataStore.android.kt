import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.dataStoreFileName
import data.getDataStore

fun createDataStore(context: Context): DataStore<Preferences> = getDataStore(
    producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)