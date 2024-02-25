package com.sumin.composeanimations.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Test() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(
                state = rememberScrollState(),
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isIncreased by remember {
            mutableStateOf(true)
        }

        val infiniteTransition = rememberInfiniteTransition()

        val size by infiniteTransition.animateFloat(
            initialValue = 200f,
            targetValue = 100f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000),
                repeatMode = RepeatMode.Reverse
            )
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isIncreased = !isIncreased
            }
        ) {
            Text(
                text = "Animate size",
            )
        }
        AnimatedContainer(
            text = "Size",
            size = size.dp
        )

        var isRect by remember {
            mutableStateOf(true)
        }
        val radius by animateIntAsState(
            targetValue = if (isRect) 4 else 50
        )
        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing)
            )
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isRect = !isRect
            }
        ) {
            Text(
                text = "Animate shape",
            )
        }
        AnimatedContainer(
            text = "Shape",
            radiusPercent = radius,
            rotation = rotation
        )

        var isSelected by remember {
            mutableStateOf(false)
        }
        val borderWidth by animateDpAsState(targetValue = if (isSelected) 4.dp else 0.dp)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isSelected = !isSelected
            }
        ) {
            Text(
                text = "Animate border",
            )
        }
        AnimatedContainer(
            text = "Border",
            borderWidth = borderWidth
        )

        var isPink by remember {
            mutableStateOf(false)
        }
        val color by animateColorAsState(targetValue = if (isPink) Color.Magenta else Color.Blue)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isPink = !isPink
            }
        ) {
            Text(
                text = "Animate color",
            )
        }
        AnimatedContainer(
            text = "Color",
            backgroundColor = color
        )

        var isTransparent by remember {
            mutableStateOf(false)
        }
        val alpha by animateFloatAsState(targetValue = if (isTransparent) 0f else 1f)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                isTransparent = !isTransparent
            }
        ) {
            Text(
                text = "Animate visibility",
            )
        }
        AnimatedContainer(
            text = "Visibility",
            alpha = alpha
        )
    }
}

@Composable
private fun AnimatedContainer(
    text: String,
    size: Dp = 200.dp,
    radiusPercent: Int = 4,
    borderWidth: Dp = 0.dp,
    backgroundColor: Color = Color.Blue,
    alpha: Float = 1f,
    rotation: Float = 0f
) {
    Box(
        modifier = Modifier
            .rotate(rotation)
            .alpha(alpha)
            .clip(RoundedCornerShape(radiusPercent))
            .border(borderWidth, Color.Black)
            .background(backgroundColor)
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}
