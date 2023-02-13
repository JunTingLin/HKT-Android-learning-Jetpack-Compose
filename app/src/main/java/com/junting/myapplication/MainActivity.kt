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
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        val (img, h1, h2) = createRefs()
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Yellow)
                .constrainAs(img) {

                }

        )
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(30.dp)
                .background(Color.Green)
                .constrainAs(h1) {
                    top.linkTo(parent.top)
                    bottom.linkTo(h2.top)
                    start.linkTo(img.end,20.dp)

                }
        )
        Box(modifier = Modifier
            .width(150.dp)
            .height(30.dp)
            .background(Color.Red)
            .constrainAs(h2) {
                top.linkTo(h1.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(h1.start)


            }
        )

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme(true) {
        MixDemo()
    }
}