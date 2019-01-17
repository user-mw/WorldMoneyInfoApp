package ru.project.worldmoneyinfo.ui.settings_screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;

import ru.project.worldmoneyinfo.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String CURRENT_TAG = "SettingsFragment";

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getActivity() != null) {
            getActivity().setTitle(R.string.settings_title);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureEntriesValues();
    }

    private void configureEntriesValues() {
        setValueFor(findPreference(getString(R.string.settings_currency_key)));
        setValueFor(findPreference(getString(R.string.settings_auto_update_key)));
        setValueFor(findPreference(getString(R.string.settings_auto_update_period_key)));
    }

    private void setValueFor(Preference preference) {
        if(preference instanceof ListPreference) {
            preference.setSummary(((ListPreference) preference).getEntry());
        } else if(preference instanceof SwitchPreference) {
            preference.setSummary(preference.getSummary());
        } else {
            Log.w(CURRENT_TAG, "Not suitable preference type");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        setValueFor(findPreference(key));
    }
}
