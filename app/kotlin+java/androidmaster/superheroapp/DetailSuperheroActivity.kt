package com.example.androidmaster.superheroapp

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    // This is the retrofit object that will be called for accessing the API
    private lateinit var retrofit: Retrofit

    // This is the binding object that will be used to access the views in the layout
    private lateinit var binding: ActivityDetailSuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        retrofit = getRetrofit()
        // Set the content view to the root view of the binding object
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    // This function will be used to get the superhero/villain information from the API
    private fun getSuperHeroInformation(id: String) {

        // This is the coroutine that will be used to create a response object for accessing the API (A Coroutine is a secondary thread that we will use to access the API)
        CoroutineScope(Dispatchers.IO).launch {

            // This is the function for getting the superhero/villain information from the API
            val superHeroDetail =
                getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)

            // If the body of the response is not null: we
            if (superHeroDetail.body() != null){

                // In the main thread, call the function that will create the UI, passing the body of the response
                runOnUiThread { createUI(superHeroDetail.body()!!) }
            }
        }
    }

    // This function will be used to create the UI for the superhero/vi
    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text = superhero.name
        prepareStats(superhero.powerstats)
        binding.tvSuperheroRealName.text = superhero.biography.fullName
        binding.tvPublisher.text = superhero.biography.publisher
    }

    // This function will prepare the stats for the superhero/villain, based on the power-stats of the superhero/villain, and update the UI
    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewPower, powerstats.power)
        updateHeight(binding.viewCombat, powerstats.combat)
    }

    // This function will update the height of the views that will show the stats (the bars), based on the value of the stat
    private fun updateHeight(view: View, stat: String){
        val params = view.layoutParams                   // Get the layout params of the view (don't care WHICH ONE EXACTLY)
        params.height = pxToDp(stat.toFloat())           // Set the height of the view to the value of the stat
        view.layoutParams = params                       // Set the layout params of the view to the new height (the updated view)
    }

    // This function will convert pixels to density independent pixels
    private fun pxToDp(px: Float):Int{

        // This is the formula to convert pixels to density independent pixels
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    // Same code as the SuperheroListActivity for getting the retrofit object
    private fun getRetrofit() = Retrofit
        .Builder()
        .baseUrl("https://superheroapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}