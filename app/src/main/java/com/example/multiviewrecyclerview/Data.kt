package com.example.multiviewrecyclerview

sealed class Data {

    data class character(val name: String,val index:Int,var aboutSection:Boolean=false) : Data()

    data class about(
        val house:String,
        val bloodStatus:String,
        val patronus: String?
        ) : Data()

}