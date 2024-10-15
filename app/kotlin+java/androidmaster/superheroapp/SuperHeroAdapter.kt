package com.example.androidmaster.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

// This class will be used to create the adapter for the recycler view
class SuperHeroAdapter(
    var superHeroList: List<SuperHeroItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    // This function will be used to fill the list with the data from the API, once the data is received
    fun updateList(superHeroList: List<SuperHeroItemResponse>) {
        // When i write "this." That means that im using the context defined in the class, but when i just put the "superHeroList" I am getting the one from the function
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    // This function will create the view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {

        // This is the layout inflater that will be used to inflate the view holder
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    // This function will count the number of items in the list (Basically, the number of superheroes)
    override fun getItemCount() = superHeroList.size

    // This function will access to the view holder and will access to the position of the item in the list
    override fun onBindViewHolder(viewholder: SuperHeroViewHolder, position: Int) {
        viewholder.bind(superHeroList[position], onItemSelected)
    }
}