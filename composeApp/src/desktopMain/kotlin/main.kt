import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.mechanika.inicjatywkaprototyp02.App
import org.mechanika.inicjatywkaprototyp02.di.AppModulePlatform
import org.mechanika.inicjatywkaprototyp02.navigation.RootComponent
import javax.swing.SwingUtilities

fun main() = application {


    val lifecycle = LifecycleRegistry()

    val root = runOnUiThread {
        RootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            appModulePlatform = AppModulePlatform()
        )
    }


    Window(onCloseRequest = ::exitApplication, title = "InicjatywkaPrototyp02") {
        App(
            root = root
        )
    }
}

internal fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    @Suppress("UNCHECKED_CAST")
    return result as T
}