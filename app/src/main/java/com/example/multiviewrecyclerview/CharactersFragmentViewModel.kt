package com.example.multiviewrecyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CharactersFragmentViewModel:ViewModel() {
    val characters = listOf<Data.character>(
        Data.character(name = "Harry Potter",0),
        Data.character("Draco Malfoy",1),
        Data.character(name = "Hermione Granger",2),
        Data.character(name = "Severus Snape",3),
        Data.character(name = "Luna Lovegood",4),
        Data.character(name = "Ron Weasley",5),
        Data.character("Tom Riddle",6),
        Data.character(name = "Sirius Black",7),
        Data.character(name = "Cedric Diggory",8),
        Data.character("Neville Longbottom",9),

    )
    val about =  listOf<Data.about>(
        Data.about(house = "Gryffindor", bloodStatus = "Half-blood", patronus = "Stag"),
        Data.about("Slytherin",   "Pure-blood", null),
        Data.about("Gryffindor", "Muggle-born", "Otter"),
        Data.about("Slytherin", "Half-blood", "Doe"),
        Data.about("Ravenclaw", "Pure-blood", "Hare"),
        Data.about("Gryffindor", "Pure-blood", "Jack Russell terrier"),
        Data.about("Slytherin", "Pure-blood", null),
        Data.about("Gryffindor", "Pure-blood", "Large dog"),
        Data.about("Hufflepuff", "Pure-blood", null),
        Data.about("Gryffindor", "Pure-blood", "Non-corporeal")
    )

    val data:Dataset = Dataset(characters, about)

    private var _dataList = MutableLiveData<MutableList<Data>>()
    val dataList : LiveData<MutableList<Data>>
        get() = _dataList

    init{
        _dataList.value = characters.toMutableList()
    }

    fun addAbout(position: Int, index: Int){
        val newList = _dataList.value
        newList?.add(position+1,about[index])
        (newList?.get(position) as Data.character).aboutSection = true
        _dataList.value = newList
    }

    fun deleteAbout(position: Int, index: Int){
        val newList = _dataList.value
        newList?.removeAt(position+1)
        (newList?.get(position) as Data.character).aboutSection = false
        _dataList.value = newList
    }

    fun aboutSection(position: Int, index: Int) {
        val character:Data.character  = _dataList.value?.get(position) as Data.character

        if(!character.aboutSection) addAbout(position,index)
        else deleteAbout(position,index)
    }


}