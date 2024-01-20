package org.mechanika.inicjatywka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import org.mechanika.inicjatywka.di.AppModulePlatform
import org.mechanika.inicjatywka.navigation.RootComponent

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            App(
                retainedComponent {
                    RootComponent(
                        it,
                        AppModulePlatform(applicationContext)
                    )
                }
            )
        }
    }
}

