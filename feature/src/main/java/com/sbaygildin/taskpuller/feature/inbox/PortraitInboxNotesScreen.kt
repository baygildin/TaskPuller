package com.sbaygildin.taskpuller.feature.inbox
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.ModalNavigationDrawer
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.SnackbarHost
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.material3.Surface
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.rememberDrawerState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import com.sbaygildin.taskpuller.feature.components.DrawerContent
//import kotlinx.coroutines.launch
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PortraitInboxNotesScreen(
//    viewModel: InboxNotesViewModel,
//) {
//    val scope = rememberCoroutineScope()
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val snackbarHostState = remember { SnackbarHostState() }
//
//    ModalNavigationDrawer(
//        gesturesEnabled = drawerState.currentValue != DrawerValue.Closed,
//        drawerContent = {
//
//            Surface(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .width(337.dp),
//                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
//                color = MaterialTheme.colorScheme.surface
//            ) {
//                DrawerContent()
//            }
//        },
//        drawerState = drawerState,
//        scrimColor = Color(0x80000000),
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Scaffold(
//            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
//            topBar = {
//                TopAppBar(
//                    title = {},
//                    navigationIcon = {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier.fillMaxHeight(),
//                            horizontalArrangement = Arrangement.SpaceAround,
//                        ) {
//                            IconButton(onClick = {
//                                scope.launch { drawerState.open() }
//                            }) {
//                                Icon(Icons.Default.Menu, contentDescription = "Меню")
//                            }
//                            Spacer(
//                                modifier = Modifier
//                                    .width(16.dp)
//                                    .weight(1f)
//                            )
//                            IconButton(onClick = {
//                            }, enabled = true) {
//                                Icon(
//                                    Icons.AutoMirrored.Filled.ArrowBack,
//                                    contentDescription = "Предыдущее изображение"
//                                )
//                            }
//                        }
//                    }
//                )
//            },
//            containerColor = MaterialTheme.colorScheme.background
//        ) { innerPadding ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(innerPadding)
//                    .background(Color.Black)
//            ) {
//            }
//        }
//    }
//}
