package com.junting.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.junting.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterDemo()
        }
    }
}

@Composable
fun CounterDemo() {
    var counter by remember { mutableStateOf(0) }


    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (textCounter, btnMinus, btnPlus) = createRefs()

        val topGuideline = createGuidelineFromTop(0.2f)
        val buttomGuideline = createGuidelineFromBottom(0.2f)

        Text(
            text = "$counter",
            fontSize = 100.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(textCounter) {
                top.linkTo(topGuideline)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Gray,
                contentColor = Color.Black,
            ),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(0),
            onClick = { counter++ },
            modifier = Modifier
                .constrainAs(btnPlus) {
                    top.linkTo(buttomGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(btnMinus.start)
                }
                .width(110.dp)) {
            Text(text = "+1",fontSize = 40.sp)
        }
        Button(
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Gray,
                contentColor = Color.Black,
            ),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(0),
            onClick = { counter-- },

            modifier = Modifier
                .constrainAs(btnMinus) {
                    top.linkTo(buttomGuideline)
                    start.linkTo(btnPlus.end)
                    end.linkTo(parent.end)
                }
                .width(110.dp)) {
            Text(text = "-1", fontSize = 40.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme(true) {
        CounterDemo()
    }
}