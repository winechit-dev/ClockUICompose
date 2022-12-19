package com.wcp.clockuicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wcp.clockuicompose.ui.Clock
import com.wcp.clockuicompose.ui.theme.ClockUIComposeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClockUIComposeTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    val milliseconds = remember {
                        System.currentTimeMillis()
                    }
                    var seconds by remember {
                        mutableStateOf((milliseconds / 1000f) % 60f)
                    }
                    var minutes by remember {
                        mutableStateOf(((milliseconds / 1000f) / 60) % 60f)
                    }
                    var hours by remember {
                        mutableStateOf((milliseconds / 1000f) / 3600f + 2f)
                    }
                    LaunchedEffect(key1 = seconds) {
                        delay(1000L)
                        minutes += 1f / 60f
                        hours += 1f / (60f * 12f)
                        seconds += 1f
                    }
                    Clock(
                        seconds = seconds,
                        minutes = minutes,
                        hours = hours
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClockUIComposeTheme {
        Greeting("Android")
    }
}