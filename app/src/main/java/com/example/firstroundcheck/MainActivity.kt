package com.example.firstroundcheck

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firstroundcheck.tasklist.TaskListScreen
import com.example.firstroundcheck.tasklist.TaskListViewModel
import com.example.firstroundcheck.tasklist.TaskRepository
import com.example.firstroundcheck.ui.theme.FirstRoundCheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(  modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()){
                val viewModel: TaskListViewModel = viewModel{
                    TaskListViewModel(TaskRepository())
                }
                TaskListScreen(viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstRoundCheckTheme {
        Greeting("Android")
    }
}