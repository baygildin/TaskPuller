package com.sbaygildin.taskpuller.feature.inbox
//
//import android.app.Activity
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.ModalNavigationDrawer
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.SnackbarHost
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.material3.Surface
//import androidx.compose.material3.rememberDrawerState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalView
//import androidx.compose.ui.unit.dp
//import androidx.core.view.WindowCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.core.view.WindowInsetsControllerCompat
//import com.sbaygildin.taskpuller.feature.components.DrawerContent
//
//@Composable
//fun LandscapeInboxNotesScreen(
//    viewModel: InboxNotesViewModel,
//) {
//    SetImmersiveMode()
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val snackbarHostState = remember { SnackbarHostState() }
//
//
//    ModalNavigationDrawer(
//        gesturesEnabled = drawerState.currentValue != DrawerValue.Closed,
//        drawerContent = {
//            Surface(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .width(337.dp),
//                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
//                color = MaterialTheme.colorScheme.surface
//            ) {
//                DrawerContent(
//                )
//            }
//        },
//        drawerState = drawerState,
//        scrimColor = Color(0x80000000),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Scaffold(
//            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
//            containerColor = MaterialTheme.colorScheme.background
//        ) { innerPadding ->
//            Row(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding)
//                    .background(Color.Black)
//            ) {
//
//            }
//        }
//    }
//}
//
//@Composable
//fun SetImmersiveMode() {
//    val view = LocalView.current
//    val activity = view.context as? Activity
//    LaunchedEffect(view) {
//        activity?.window?.let { window ->
//            WindowCompat.setDecorFitsSystemWindows(window, false)
//            val controller = WindowInsetsControllerCompat(window, view)
//            controller.hide(WindowInsetsCompat.Type.systemBars())
//            controller.systemBarsBehavior =
//                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//        }
//    }
//}