package com.example.androidmaster.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

// This class will be used to bind the data to the view holder. Basically, we are "passing" the data to the view holder
class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // This is the binding object that will be used to access the views in the layout
    private var binding = ItemSuperheroBinding.bind(view)

    // This function is going to be in charge of binding the data (to the view holder)
    fun bind(superHeroItemResponse: SuperHeroItemResponse, onItemSelected: (String) -> Unit){

        // This is the superhero name that we are going to show in the view holder
        binding.tvSuperHeroName.text = superHeroItemResponse.name

        // This is the superhero image that we are going to show in the view holder (using Picasso library)
        Picasso.get().load(superHeroItemResponse.superheroImage.url).into(binding.ivSuperHero)

        // This is the click listener that will be used to get the superhero id once the user clicks on the superhero
        binding.root.setOnClickListener { onItemSelected(superHeroItemResponse.superHeroId) }
    }
}