package com.arturmaslov.lkz_droid

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arturmaslov.lkz_droid.ui.theme.LKZdroidTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.arturmaslov.lkz_droid.utils.Constants.BASE_URL
import kotlinx.coroutines.CoroutineScope

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LKZdroidTheme {
                var selectedTab by remember { mutableIntStateOf(0) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        TabRow(
                            selectedTabIndex = selectedTab
                        ) {
                            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("Web Dictionary") })
                            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("Saved words") })
                        }
                    },
                    content = { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding),
                        ) {
                            MainScreen(
                                selectedTab = selectedTab
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    selectedTab: Int
) {
    when (selectedTab) {
        0 -> OnlineTab()
//        1 -> OfflineTab()
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun OnlineTab() {
    Column(Modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true
                        builtInZoomControls = true
                        displayZoomControls = false
                        cacheMode = WebSettings.LOAD_DEFAULT // Load from cache when available
                        loadsImagesAutomatically = true
                    }

                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)

                            view?.setInitialScale(380)
                            view?.scrollTo(0,0) // Scroll to the top-left corner (x=0, y=0)
                        }
                    }

                    loadUrl(BASE_URL)
                }
            },
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = { /* Save word logic */ },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Save Word")
        }
    }
}

//@Composable
//fun OfflineTab(viewModel: WordViewModel = WordViewModel()) {
//    val words = viewModel.savedWords.collectAsState(initial = emptyList())
//
//    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
//        items(words.value) { word ->
//            Text(text = word, modifier = Modifier.padding(8.dp))
//        }
//    }
//}

