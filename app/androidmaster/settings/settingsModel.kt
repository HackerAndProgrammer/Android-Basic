package com.example.androidmaster.settings

// This class will have all the settings entered by the user
data class settingsModel(
    var volume: Int,
    var bluetooth: Boolean,
    var vibration: Boolean,
    var darkMode: Boolean
) {
}