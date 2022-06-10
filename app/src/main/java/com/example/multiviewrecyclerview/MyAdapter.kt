package com.example.multiviewrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val clickListener: CharactersFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var _data:Dataset
    private lateinit var _dataList:List<Data>
    fun setData(data: List<Data>){
        _dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == VIEW_CHARACTER){
            val inflater:LayoutInflater = LayoutInflater.from(parent.context)
            val view =  inflater.inflate(R.layout.item_character,parent,false)
            return characterViewHolder(view)
        }

        val inflater:LayoutInflater = LayoutInflater.from(parent.context)
        val view =  inflater.inflate(R.layout.item_about,parent,false)
        return aboutViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(_dataList[position]){
            is Data.about -> (holder as aboutViewHolder).bind(position)
            is Data.character -> (holder as characterViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int {
        Log.d("adapter",_dataList.size.toString())
       return _dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(_dataList[position]){
            is Data.about -> VIEW_ABOUT
            is Data.character -> VIEW_CHARACTER
        }
    }

    inner class characterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val name = itemView.findViewById<TextView>(R.id.name)

        init{
            itemView.setOnClickListener(this)
        }
        fun bind(position: Int){
            val character = _dataList[position] as Data.character
            name.text = character.name
        }

        override fun onClick(p0: View?) {
            //_dataList.add(adapterPosition+1,_data.about[adapterPosition])
            clickListener.onCharacterClick(adapterPosition,(_dataList[adapterPosition] as Data.character).index)
        }
    }

    inner class aboutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val house = itemView.findViewById<TextView>(R.id.house)
        val bloodStatus = itemView.findViewById<TextView>(R.id.bloodStatus)
        val patronus = itemView.findViewById<TextView>(R.id.patronus)

        fun bind(position: Int){
            val about = _dataList[position] as Data.about
            house.text = about.house
            bloodStatus.text = about.bloodStatus
            patronus.text = about.patronus
        }
    }

    companion object{
        const val  VIEW_CHARACTER = 0
        const val  VIEW_ABOUT = 1
    }

    interface onCharacterClickListener{
        fun onCharacterClick(position: Int,index:Int)
    }
}

