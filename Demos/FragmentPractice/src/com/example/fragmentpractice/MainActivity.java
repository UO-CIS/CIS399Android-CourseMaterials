// Written by Brian Bird, 7/8/14
package com.example.fragmentpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Load the layout that the fragment will go into
        setContentView(R.layout.activity_main);
        
        // Add the fragment to the RelativeLayout inside activity_maim
        getFragmentManager().beginTransaction()
        	.add(R.id.rootLayout, new FragmentOne()).commit();
    }
}
