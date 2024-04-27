package de.dennisguse.opentracks.settings;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import de.dennisguse.opentracks.R;
import de.dennisguse.opentracks.settings.skiProfile.SkiProfileFavoriteSkiResortFragment;
import de.dennisguse.opentracks.settings.skiProfile.SkiProfileMaintenanceFragment;
import de.dennisguse.opentracks.settings.skiProfile.SkiProfileSharpeningFragment;
import de.dennisguse.opentracks.settings.skiProfile.SkiProfileStatisticsFragment;
import de.dennisguse.opentracks.settings.skiProfile.SkiProfileWaxingFragment;

public class SkiProfileSettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.settings_ski_profile);

        findPreference(getString(R.string.ski_profile_favorite_resort_ski_resort_title)).setOnPreferenceClickListener(preference -> {
            ((SettingsActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.settings_fragment, new SkiProfileFavoriteSkiResortFragment()).addToBackStack(getString(R.string.ski_profile_favorite_resort_ski_resort_title)).commit();
            return true;
        });

        findPreference(getString(R.string.ski_profile_statistics_title)).setOnPreferenceClickListener(preference -> {
            ((SettingsActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.settings_fragment, new SkiProfileStatisticsFragment()).addToBackStack(getString(R.string.ski_profile_statistics_title)).commit();
            return true;
        });

        findPreference(getString(R.string.ski_profile_sharpening_info_title)).setOnPreferenceClickListener(preference -> {
            ((SettingsActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.settings_fragment, new SkiProfileSharpeningFragment()).addToBackStack(getString(R.string.ski_profile_sharpening_info_title)).commit();
            return true;
        });

        findPreference(getString(R.string.ski_profile_waxing_info_title)).setOnPreferenceClickListener(preference -> {
            ((SettingsActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.settings_fragment, new SkiProfileWaxingFragment()).addToBackStack(getString(R.string.ski_profile_waxing_info_title)).commit();
            return true;
        });

        findPreference(getString(R.string.ski_profile_ski_maintenance_title)).setOnPreferenceClickListener(preference -> {
            ((SettingsActivity) getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.settings_fragment, new SkiProfileMaintenanceFragment()).addToBackStack(getString(R.string.ski_profile_ski_maintenance_title)).commit();
            return true;
        });
    }
}
