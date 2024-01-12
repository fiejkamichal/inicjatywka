package org.mechanika.inicjatywkaprototyp02

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.retainedComponent
import org.mechanika.inicjatywkaprototyp02.di.AppModulePlatform
import org.mechanika.inicjatywkaprototyp02.navigation.RootComponent

class MainActivity : ComponentActivity() {
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

