package com.lowbottgames.au.perth.traffic.cam.fragment

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.lowbottgames.au.perth.traffic.cam.BuildConfig
import com.lowbottgames.au.perth.traffic.cam.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        context?.let {
            PreferenceManager.setDefaultValues(it, R.xml.preferences, false)
        }

        val appVersionPreference: Preference? = findPreference("app_version_preference")
        appVersionPreference?.run {
            summary = BuildConfig.VERSION_NAME
        }
    }

}