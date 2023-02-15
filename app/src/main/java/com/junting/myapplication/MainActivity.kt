package com.junting.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.junting.myapplication.ui.theme.MyApplicationTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceDemo()
        }
    }
}

@Composable
fun DiceDemo() {
    val diceImgs = arrayOf(
        R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6
    )
    var randNum by remember { mutableStateOf(0) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (imgDice, btnRandom) = createRefs()

        Image(painter = painterResource(id = diceImgs[randNum]),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(imgDice) {
                    top.linkTo(parent.top, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
                .size(300.dp)
        )

        Button(onClick = {
            randNum = Random().nextInt(6)
            Log.d("Junting", randNum.toString())
        }, modifier = Modifier.constrainAs(btnRandom) {
            bottom.linkTo(parent.bottom, 50.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text(text = "隨機", fontSize = 80.sp)

        }
    }

}

fun calBMI(height: Double, weight: Double): Double {
    return weight / Math.pow(height / 100, 2.0)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme(true) {
        DiceDemo()
    }
}