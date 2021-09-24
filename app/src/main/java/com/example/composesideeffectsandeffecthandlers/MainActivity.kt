package com.example.composesideeffectsandeffecthandlers

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesideeffectsandeffecthandlers.ui.theme.ComposeSideEffectsAndEffectHandlersTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/* Side effects in programming : Everything that escapes the scope of a function*/
/* Side effects in compose : Everything inside a composable function that does not have anything to do with compose*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    ComposeSideEffectsAndEffectHandlersTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            /*MyComposable()*/

            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            Scaffold(scaffoldState = scaffoldState) {
                var counter by remember {
                    mutableStateOf(0)
                }
                if (counter % 5 == 0 && counter > 0) {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Hello")
                    }
                    LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                        scaffoldState.snackbarHostState.showSnackbar("Hello")
                    }
                }
                Button(onClick = { counter++ }) {
                    Text(text = "Click me: $counter")
                }
            }

            /*val scaffoldState = rememberScaffoldState()

            Scaffold(scaffoldState = scaffoldState) {
                var counter = produceState(initialValue = 0) {
                    delay(3000L)// delayed for demonstration purpose
                    value = 4
                }
                if (counter.value % 5 == 0 && counter.value > 0) {
                    *//*scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Hello")
                    }*//*
                    LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
                        scaffoldState.snackbarHostState.showSnackbar("Hello")
                    }
                }
                Button(onClick = {  }) {
                    Text(text = "Click me: ${counter.value}")
                }
            }*/
        }
    }
}

/*var i = 0*/

/*
// Side effect
@Composable
fun MyComposable() {
    SideEffect {
        i++
    }

    Button(onClick = { *//*TODO*//* }, modifier = Modifier.wrapContentSize(), enabled = true) {
        Text(text = "Click me")
    }
}*/

/*
// Disposable effect
@Composable
fun MyComposable(backPressedDispatcher: OnBackPressedDispatcher) {
    val callback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                TODO("Not yet implemented")
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }

    Button(onClick = { *//*TODO*//* }, modifier = Modifier.wrapContentSize(), enabled = true) {
        Text(text = "Click me")
    }
}*/

@Preview(showBackground = true, name = "Light mode")
@Preview(showBackground = true, name = "Night mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    MyApp()
}