package com.junting.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.junting.myapplication.data.Feature
import com.junting.myapplication.data.PharmacyInfo
import com.junting.myapplication.util.OkHttpUtil
import com.junting.myapplication.util.OkHttpUtil.Companion.mOkHttpUtil
import okhttp3.*
import java.io.IOException
import java.util.*


private var pharmacyList: List<Feature>? = null

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            Demo()
        }
        getPharmacyData()
        Log.d("junting", "取完資料")

    }
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun Demo() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("junting練習") },
                )
            }
        ) {
            //畫面內容
            pharmacyList?.let { it1 ->
                PharmacyListScreen(it1)
            }

        }

    }

    @Composable
    fun PharmacyListScreen(pharmacyList: List<Feature>) {
        LazyColumn {
            items(pharmacyList) { feature ->
                PharmacyCard(feature)
            }
        }
    }

    @Composable
    fun PharmacyCard(feature: Feature) {
        Row(
            Modifier
                .padding(all = 20.dp)
                .background(MaterialTheme.colors.background)
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.dice1),
                contentDescription = "",

                )

            Column(
                Modifier
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = feature.properties.name,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(
                    Modifier.height(4.dp)
                )
                Text(
                    text = feature.properties.phone,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }


    @Composable
    fun SimpleCircularProgressComponent(isVisible: Boolean) {
        val commentsAlpha = if (isVisible) 1f else 0f
        CircularProgressIndicator(
            modifier = Modifier
                .padding(16.dp)
                .alpha(commentsAlpha)
        )
    }

    private fun getPharmacyData() {
        //顯示忙碌圈圈
//    SimpleCircularProgressComponent(isVisible = true)

        mOkHttpUtil.getAsync(PHARMACIES_DATA_URL, object : OkHttpUtil.ICallback {
            override fun onResponse(response: Response) {
                val pharmaciesData = response.body?.string()

                var pharmacyInfo: PharmacyInfo =
                    Gson().fromJson(pharmaciesData, PharmacyInfo::class.java)
                pharmacyList = pharmacyInfo.features
                Log.d("junting", pharmaciesData ?: "空")

            }

            override fun onFailure(e: IOException) {
                Log.d("junting", "onFailure: $e")

            }
        })

    }

}

