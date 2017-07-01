package edu.uoregon.bbird.lifecycleexperiment;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by User on 7/9/2015.
 */
public class SettingsActivity  extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
    }
}
