package com.sbaygildin.taskpuller.feature.timer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TimeRollerScreen() {


    val numbersList = List(60) { it.toString().padStart(2, '0') }
    val hoursList = numbersList.slice(0..23)

    var selectedHour by remember { mutableStateOf("00") }
    var selectedMinute by remember { mutableStateOf("00") }
    var selectedSecond by remember { mutableStateOf("00") }


    Row(
        Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TimePickerColumn(items = hoursList) { selectedHour = it }

        TimePickerColumn(items = numbersList) { selectedMinute = it }

        TimePickerColumn(items = numbersList) { selectedSecond = it }


    }
}

@Composable
fun TimePickerColumn(
    items: List<String>,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    val listState = rememberLazyListState()
    val visibleItems = 5
    val middleIndex = visibleItems / 2

    val paddedItems = List(middleIndex) { "" } + items + List(middleIndex) { "" }

    LaunchedEffect(listState.firstVisibleItemIndex) {
        if (listState.firstVisibleItemScrollOffset  < 10) {
            val index = listState.firstVisibleItemIndex + middleIndex
            if (index in items.indices) {
                onValueChange(items[index])
            }
        }

    }
    Box(modifier = modifier) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .size(width = 60.dp, height = 120.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(paddedItems.size) { index ->
                val item = paddedItems[index]
                val isSelected = index == listState.firstVisibleItemIndex + middleIndex
                Text(
                    text = item,
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview
@Composable
fun TimeRollerScreenPreview() {
    TimeRollerScreen()
}