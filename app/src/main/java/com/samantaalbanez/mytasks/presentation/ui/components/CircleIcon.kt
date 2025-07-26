package com.samantaalbanez.mytasks.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.samantaalbanez.mytasks.R

@Composable
fun CircleIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.circle),
        contentDescription = null,
        modifier = modifier
    )
}
