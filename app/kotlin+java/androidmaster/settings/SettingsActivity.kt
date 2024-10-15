package com.example.androidmaster.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// This will be the name of the DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    // This Companion Object will have all the keys required for saving the options entered by the user
    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_DARK_MODE = "key_dark_mode"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_VIBRATION = "key_vibration"
    }

    // Set the binding object so we can access to the UI (more easily than with findViewById)
    private lateinit var binding: ActivitySettingsBinding
    private var firstTime: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Inflate the lay out and set the binding as a root view
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Launch the Coroutine to get the settings
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect { settingsModel ->

                // If there are no settings configured by the user, it will use the default values
                if (settingsModel != null) {
                    // Compile on the main thread
                    runOnUiThread {
                        binding.switchDarkMode.isChecked = settingsModel.darkMode
                        binding.rsVolume.setValues(settingsModel.volume.toFloat())
                        binding.switchBluetooth.isChecked = settingsModel.bluetooth
                        binding.switchVibrations.isChecked = settingsModel.vibration
                        firstTime = !firstTime //Now, it's false, because we already got the settings
                    }
                }
            }
        }
        initUI()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // This function will initialize the UI (it has a Coroutine inside, because the function we use for saving the volume, is a suspend function)
    private fun initUI() {
        binding.rsVolume.addOnChangeListener { _, value, _ -> // When the rangeSlider is changed, the value is returned
            Log.i("AndroidDevJuli", "The volume is: $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }

        // This code will get the Dark Mode activated (True, or False) entered from the user, and will save it in the DataStore
        binding.switchDarkMode.setOnCheckedChangeListener { _, value ->

            // If the switch is true, we call the function that paints the screen in Dark Mode
            if (value){
                enableDarkMode()
            }else{
                disableDarkMode()
            }

            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, value)
            }
        }

        // This code will get the bluetooth (True, or False) entered from the user, and will save it in the DataStore
        binding.switchBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH, value)
            }
        }

        // This code will get the vibration (True, or False)entered from the user, and will save it in the DataStore
        binding.switchVibrations.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VIBRATION, value)
            }
        }
    }

    // This function will save the volume entered by the user in the DataStore
    private suspend fun saveVolume(value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    // This function will save the checks (Dark Mode, Bluetooth, and Vibration) in the DataStore
    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    // This function will get ALL the settings, not just Checkbox, or RangleSlider, literally, ALL of them
    private fun getSettings(): Flow<settingsModel?> {
        return dataStore.data.map { preferences ->
            // Getting access to the settingsModel with the values
            settingsModel(
                volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: false,
                darkMode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: true
            )
        }
    }

    // This function will enable the Dark Mode
    private fun enableDarkMode() {
        // Paints the screen in Dark Mode
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        // Paints the screen in Light Mode
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}