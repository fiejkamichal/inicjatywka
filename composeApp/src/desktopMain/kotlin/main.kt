import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.mechanika.inicjatywka.App
import org.mechanika.inicjatywka.di.AppModulePlatform
import org.mechanika.inicjatywka.navigation.RootComponent
import javax.swing.SwingUtilities

fun main() = application {


    val lifecycle = LifecycleRegistry()

    val appModulePlatform = AppModulePlatform()

    val root = runOnUiThread {
        RootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            appModulePlatform = appModulePlatform
        )
    }


    Window(
        onCloseRequest = ::exitApplication,
        title = "Inicjatywka",
        state = rememberWindowState(placement = WindowPlacement.Maximized),
    ) {
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