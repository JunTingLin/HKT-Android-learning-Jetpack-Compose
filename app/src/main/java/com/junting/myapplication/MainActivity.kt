package com.junting.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.junting.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MixDemo()
        }
    }
}

@Composable
fun MixDemo() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        val (yellowBox, greenBox, redBox) = createRefs()

        //對齊父容器
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        //對齊父容器，加入 margin 間距
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Green)
            .constrainAs(greenBox) {
                top.linkTo(parent.top,150.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start,150.dp)
                end.linkTo(parent.end)
            })

        //對齊其他元件，並加入 margin 間距
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Red)
            .constrainAs(redBox) {
                top.linkTo(greenBox.bottom)
                end.linkTo(greenBox.end)
            })
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme(true) {
        MixDemo()
    }
}