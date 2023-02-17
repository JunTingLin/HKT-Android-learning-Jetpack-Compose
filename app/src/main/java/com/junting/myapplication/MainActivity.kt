package com.junting.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {TopComposable()}


    }

}

class MyViewModel(context: Context) : ViewModel() {
    private val _sp = context.getSharedPreferences("my_sp_file", Context.MODE_PRIVATE)
    private val _defaultCount = _sp.getInt("DEFAULT_COUNT", 0)
    private val _count = MutableLiveData(_defaultCount)

    val count: LiveData<Int>
        get() = _count

    fun addCount() {
        val temp = _count.value?.plus(1)
        _count.postValue(temp)
        _sp.edit {
            temp?.let { putInt("DEFAULT_COUNT", it) }
        }
    }
}

class MyViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(context) as T
    }
}

@Composable
fun TopComposable() {
    val context = LocalContext.current
    val viewModel: MyViewModel = viewModel(factory = MyViewModelFactory(context))
    val count by viewModel.count.observeAsState(0)

//    val addCount = { viewModel.addCount() }
//    Demo(count, addCount)
//    Demo(count, addCount = { viewModel.addCount() })
    Demo(count) {
        viewModel.addCount()
    }
}

@Composable
fun Demo(count: Int, addCount: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "$count",
            modifier = Modifier.padding(10.dp),
            fontSize = 30.sp
        )
        Button(
            modifier = Modifier
                .width(150.dp)
                .height(100.dp),
            onClick = {
                addCount()
            }
        ) {
            Text("＋１")
        }
    }
}

