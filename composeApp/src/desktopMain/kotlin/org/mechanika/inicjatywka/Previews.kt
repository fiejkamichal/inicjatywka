package org.mechanika.inicjatywka

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.mechanika.inicjatywka.di.AppModulePlatform
import org.mechanika.inicjatywka.navigation.RootComponent
import javax.swing.SwingUtilities

@Preview
@Composable
fun Preview() {
    val lifecycle = LifecycleRegistry()
    val appModulePlatform = AppModulePlatform()
    val root = runOnUiThread {
        RootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            appModulePlatform = appModulePlatform
        )
    }
    App(
        root = root
    )
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