package com.junting.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.junting.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo()
        }
    }
}

@Composable
fun Demo() {
    AsyncImage(
        model = "https://i.imgur.com/hWMGvED.jpg",
        contentDescription = null,
        modifier = Modifier
            .size(100.dp, 400.dp)
            .background(Color.Red),
        contentScale = ContentScale.FillBounds

    )
}

fun calBMI(height: Double, weight: Double): Double {
    return weight / Math.pow(height/100, 2.0)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme(true) {
        Demo()
    }
}