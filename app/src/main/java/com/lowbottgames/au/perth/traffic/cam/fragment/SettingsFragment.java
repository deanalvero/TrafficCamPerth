package com.lowbottgames.au.perth.traffic.cam.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import com.lowbottgames.au.perth.traffic.cam.BuildConfig;
import com.lowbottgames.au.perth.traffic.cam.R;

public class SettingsFragment extends PreferenceFragmentCompat {

//    private static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);

        Context context = getContext();
        if (context != null) {
            PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
        }

        Preference appVersionPreference = findPreference("app_version_preference");
        if (appVersionPreference != null) {
            appVersionPreference.setSummary(BuildConfig.VERSION_NAME);
        }

    }

}
