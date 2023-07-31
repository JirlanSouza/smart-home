package com.js.smarthome.home.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.js.smarthome.R
import com.js.smarthome.home.ui.components.HomeItemCard

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(padding: PaddingValues) {
    val gap = 12.dp

    FlowRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(gap),
        modifier = Modifier
            .padding(start = gap, end = gap, top = padding.calculateTopPadding())
            .fillMaxWidth()
    ) {

        repeat(times = 8, action = {
            Box(modifier = Modifier
                .size(120.dp)
                .weight(1f, true)
                .padding(bottom = gap)
            ) {
                HomeItemCard(
                    name = "Shower",
                    iconPainter = painterResource(id = R.drawable.shower_icon)
                )
            }
        })

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(PaddingValues())
}