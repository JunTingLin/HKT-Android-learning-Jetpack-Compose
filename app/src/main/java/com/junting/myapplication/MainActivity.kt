package com.junting.myapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo()
        }
    }
}

sealed class Routes(val route: String) {
    object APage : Routes("a_page")
    object BPage : Routes("b_page")
    object CPage : Routes("c_page")
}

@Composable
fun Demo(startDestination: String = Routes.APage.route) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            Routes.APage.route
        ) {
            APage(navController)
        }

        composable(
            Routes.BPage.route
        ) {
            BPage(navController)
        }

        composable(
            Routes.CPage.route
        ) {
            CPage(navController)
        }
    }

}

@Composable
fun APage(navController: NavHostController) {
    BasePage("A 頁面內容", "前往") {
        navController.navigate(Routes.BPage.route)
    }
}

@Composable
fun BPage(navController: NavHostController) {
    BasePage("B 頁面內容", "前往") {
        navController.navigate(Routes.CPage.route)
    }
}

@Composable
fun CPage(navController: NavHostController) {
    BasePage("C 頁面內容", "返回") {
        navController.navigateUp()
    }
}

@Composable
fun BasePage(pageContent: String, btnContent: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = pageContent,
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 100.dp)
        )
        Button(
            modifier = Modifier
                .width(150.dp)
                .height(100.dp),
            onClick = {
                onClick()
            }
        ) {
            Text(btnContent)
        }
    }
}