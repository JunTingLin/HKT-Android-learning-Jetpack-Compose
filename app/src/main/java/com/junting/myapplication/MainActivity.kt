package com.junting.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.junting.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMIDemo()
        }
    }
}

@Composable
fun BMIDemo() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf("") }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (inputHeight, inputWeight, textResult, btnCal, btnClear) = createRefs()

        TextField(
            value = "$height",
            onValueChange = { height = it },
            singleLine = true,
            label = { Text("身高(cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.constrainAs(inputHeight) {
                top.linkTo(parent.top,120.dp)
                bottom.linkTo(inputWeight.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        TextField(
            value = "$weight",
            onValueChange = { weight = it },
            singleLine = true,
            label = { Text("體重(kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.constrainAs(inputWeight) {
                top.linkTo(inputHeight.bottom)
                bottom.linkTo(textResult.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            text = "結果:" + bmi ?: "",
            fontSize = 30.sp,
            modifier = Modifier.constrainAs(textResult) {
                top.linkTo(inputWeight.bottom,120.dp)
                bottom.linkTo(btnCal.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Button(onClick = { bmi = calBMI(height.toDouble(), weight.toDouble()).toString() },
            modifier = Modifier.constrainAs(btnCal) {
                top.linkTo(textResult.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(btnClear.start)
            }) {
            Text(text = "計算", fontSize = 50.sp)
        }
        Button(onClick = { weight = "";height = "";bmi = "" },
            modifier = Modifier.constrainAs(btnClear) {
                top.linkTo(textResult.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(btnCal.end)
                end.linkTo(parent.end)
            }) {
            Text(text = "清空", fontSize = 50.sp)
        }


    }
}

fun calBMI(height: Double, weight: Double): Double {
    return weight / Math.pow(height/100, 2.0)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme(true) {
        BMIDemo()
    }
}