package com.example.androidmaster.superheroapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivitySuperHeroListBinding
import com.example.androidmaster.superheroapp.DetailSuperheroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    // This is the binding object that will be used to access the views in the layout
    private lateinit var binding: ActivitySuperHeroListBinding

    // This is the retrofit object that will be called for accessing the API
    private lateinit var retrofit: Retrofit

    // This is the adapter that will be used to create the recycler view
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Set the content view to the root view of the binding object
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // This function will be used to initialize the UI components
    private fun initUI() {

        // This is all the search view programming: first, we set the hint text, then we set the listener: when the user clicks the search button, we call the searchByName function
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        // Access the adapter class
        adapter =
            SuperHeroAdapter { superheroId -> navigateToDetail(superheroId) }          // Same as: { navigateToDetail(it) }

        // Set the adapter to the recycler view
        binding.rvSuperHero.setHasFixedSize(true)

        // Set the layout manager to the recycler view
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)

        // Set the adapter to the recycler view
        binding.rvSuperHero.adapter = adapter

        // Set the no-superhero-searched message to visible
        binding.tvNoSuperHeroSearched.isVisible = true
    }

    // Getting access to the characters from the API, based on the "name" parameter
    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true               // Set the progress bar to visible

        // If the progress bar is visible, we hide the text that tells the user: "No superhero/villain searched"
        if (binding.progressBar.isVisible) {
            binding.tvNoSuperHeroSearched.isVisible = false
        }

        // This is the coroutine that will be used to access the API
        CoroutineScope(Dispatchers.IO).launch {

            // This is the response object that will be used to access the API
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperheroes(query)

            if (myResponse.isSuccessful) { // If the search of the user is successful, we:
                Log.i("androidmaster", "It works!👌👌")               // Log a success message
                val response: SuperHeroDataResponse? =
                    myResponse.body()      // This is the body of the response, which is the data that we want to access (the characters)
                runOnUiThread {
                    // Once the user searches for something, we hide the message that tells the user that nothing has been searched
                    binding.tvNoSuperHeroSearched.isVisible = false
                }
                if (response != null) {       // If the response is not null, we:
                    Log.i(
                        "androidmaster",
                        response.toString()
                    ) // Log the response (If is not success, we are in big trouble)
                    runOnUiThread {                  // This is the main thread, the one we always use to access the UI components
                        adapter.updateList(response.superheroes) // Update the adapter with the new data
                        binding.progressBar.isVisible = false
                    }
                } else {
                    // On the main thread, we call the function that will tell the user that their query is not valid
                    runOnUiThread {
                        handleEmptyResponse()
                    }
                }
            } else {                       // If is not successful, we:
                Log.i("androidmaster", "It does not work...😢😢")   // Log a failure messag
                // Call the function that will tell the user that their query is not valid (on the main thread, of course)
                runOnUiThread {
                    handleEmptyResponse()
                }
            }
        }
    }

    // This function will be called when the user enters a-non-valid-text in the searchview
    private fun handleEmptyResponse() {
        // Hide the progress bar and the recycler view
        binding.rvSuperHero.visibility = View.GONE
        binding.progressBar.visibility = View.GONE

        // Clean the searchview and show the keyboard
        binding.searchView.setQuery("", false)
        binding.searchView.requestFocus()
    }

    // This function will be used to get the retrofit object
    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://superheroapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // This function will send the user to the detail activity (That is, when the user clicks on a superhero)
    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}