package com.example.multiviewrecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CharactersFragment : Fragment(), MyAdapter.onCharacterClickListener{

    private lateinit var viewModel:CharactersFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_characters, container, false)



        viewModel = ViewModelProvider(this).get(CharactersFragmentViewModel::class.java)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val adapter = MyAdapter(this)

        viewModel.dataList.observe(viewLifecycleOwner){newDataList->
            adapter.setData(newDataList)
            recyclerView.adapter = adapter
        }

        return view

    }

    override fun onCharacterClick(position:Int,index:Int) {
        viewModel.aboutSection(position,index)
        Toast.makeText(activity,"Item $position clicked",Toast.LENGTH_SHORT).show()
    }


}