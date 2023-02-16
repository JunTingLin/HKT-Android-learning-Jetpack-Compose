package com.junting.myapplication

fun main(){
    var name = "jun1"
    HelloContent2("tim",{a-> name=a;println(a) })
    println(name)
}

fun HelloContent2(name: String, onNameChange: (String) -> Unit) {
    println(name)
    onNameChange("jun2")

}