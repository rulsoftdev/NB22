package org.rulsoft.ap.nb22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.rulsoft.ap.nb22.presentation.common.ui.NB22App
import org.rulsoft.ap.nb22.presentation.common.ui.NB22Screen
import org.rulsoft.ap.nb22.presentation.common.ui.theme.NB22Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NB22App()
        }
    }
}
