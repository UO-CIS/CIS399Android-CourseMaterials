// Written by Brian Bird, 7/8/14
package com.example.fragmentpractice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentTwo extends Fragment implements OnClickListener {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		// Instantiate the View described by fragment_two.xml
		View view = inflater.inflate(R.layout.fragment_two, container, false);
		// Set up the Listener for the Button
		Button button = (Button)view.findViewById(R.id.goToOneButton);
		button.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// Replace FragmentOne with FragmentTwo
		 getFragmentManager().beginTransaction()
     	.replace(R.id.rootLayout, new FragmentOne()).commit();
		
	}
}