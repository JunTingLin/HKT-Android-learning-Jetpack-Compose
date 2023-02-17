package com.junting.myapplication

fun main(){
    val dog = Dog("junting",-50)
    println(dog)
    dog.weight = -80
    println(dog)
}



class Dog(val name:String,weight_param:Int){

    var weight = weight_param
        set(value){
            if(value>0) field = value
        }


    override fun toString(): String {
        return "Dog(name='$name', weight=$weight)"
    }

}