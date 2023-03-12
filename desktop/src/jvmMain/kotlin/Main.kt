import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.mvikotlin.core.utils.setMainThreadId
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.mocoding.pokedex.core.di.initKoin
import com.mocoding.pokedex.ui.ContentView
import com.mocoding.pokedex.ui.root.RootComponent
import javax.swing.SwingUtilities

fun main() {
    initKoin(enableNetworkLogs = false)

//    overrideSchedulers(main = Dispatchers.Main::asScheduler)

    val rootComponent = invokeOnAwtSync {
        setMainThreadId(Thread.currentThread().id)

        val lifecycle = LifecycleRegistry()

        val rootComponent = RootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            storeFactory = DefaultStoreFactory(),
        )

        lifecycle.resume()

        rootComponent
    }

    application {
        Window(onCloseRequest = ::exitApplication) {
            ContentView(
                component = rootComponent
            )
        }
    }
}

fun <T> invokeOnAwtSync(block: () -> T): T {
    var result: T? = null
    SwingUtilities.invokeAndWait { result = block() }

    @Suppress("UNCHECKED_CAST")
    return result as T
}