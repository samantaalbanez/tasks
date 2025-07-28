package com.samantaalbanez.mytasks.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CardTask(label: String, count: Int, color: Color) {
    Card(
        modifier = Modifier
            .width(110.dp)
            .height(84.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            Modifier.padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = count.toString(),
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Color.White
            )
            Text(
                text = label, style = MaterialTheme.typography.labelMedium, color = Color.White,
                maxLines = 1,
            )
        }
    }
}
