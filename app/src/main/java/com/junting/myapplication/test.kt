package com.junting.myapplication.ui.theme

fun sum(x: (Int,Int)->Int): Int {
    return x.invoke(1,2)
}


fun main() {
    print(sum({a:Int,b:Int-> return@sum a+b }))
}