package de.dennisguse.opentracks.settings.skiProfile;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import de.dennisguse.opentracks.R;

public class SkiProfileStatisticsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.fragment_ski_profile_statistics);

        ListPreference timePeriodPreference = findPreference("time_period");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        String currentValue = sharedPreferences.getString("time_period", "Total");
        updateStatistics(currentValue.equals("Total"), sharedPreferences);
        timePeriodPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String selectedValue = (String) newValue;
                updateStatistics(Objects.equals(selectedValue, "Total"), sharedPreferences);
                return true;
            }
        });


    }

    private void updateStatistics(boolean isTotal, SharedPreferences sharedPreferences) {
        double totalKmSkied = new DatabaseHelper(getContext()).getTotalDistanceSum(isTotal ? 0 : getLastSeasonTime());
        double intervalOfWaxing = Double.parseDouble(sharedPreferences.getString("waxing_interval", "30"));
        double intervalOfSharpening = Double.parseDouble(sharedPreferences.getString("sharpening_interval", "30"));
        int numOfWaxing = (int) (totalKmSkied / intervalOfWaxing);
        int numOfSharpening = (int) (totalKmSkied / intervalOfSharpening);
        findPreference("waxing")
                .setSummaryProvider(
                        preference -> {
                            return numOfWaxing + " Waxing activities performed.";
                        }
                );

        findPreference("sharpening")
                .setSummaryProvider(
                        preference -> {
                            return numOfSharpening + " Sharpening activities performed.";
                        }
                );
    }

    private long getLastSeasonTime() {
        long currentTimeInMillis = System.currentTimeMillis();
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTimeInMillis), ZoneOffset.UTC);
        LocalDateTime oneYearAgoDateTime = currentDateTime.minusYears(1);
        return oneYearAgoDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

}