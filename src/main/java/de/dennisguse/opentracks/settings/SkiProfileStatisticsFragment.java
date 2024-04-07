package de.dennisguse.opentracks.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.Duration;

import de.dennisguse.opentracks.R;

public class SkiProfileStatisticsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.fragment_ski_profile_statistics);

        int totalKmSkied = 50; // THIS IS DEPENDANT ON THE WORK OF OTHER GROUP
        int intervalOfWaxing = 10; //THIS HAS BEEN DONE BY OUR GROUP. WILL IMPLEMENT HERE ONCE WE HAVE TOTAL KM OF SKI.
        int intervaleOfSharpening = 5; //THIS HAS BEEN DONE BY OUR GROUP. WILL IMPLEMENT HERE ONCE WE HAVE TOTAL KM OF SKI.
        int numOfWaxing = totalKmSkied / intervalOfWaxing;
        int numOfSharpening = totalKmSkied / intervaleOfSharpening;
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

}