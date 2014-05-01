package com.example.aede;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	Context context;

	public TabsPagerAdapter(FragmentManager fm,Context context) {
		super(fm);
		this.context = context;
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Post fragment activity
			return new Post(context);
		case 1:
			// Member fragment activity
			return new Member(context);
		case 2:
			// Member fragment activity
			return new Company(context);
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
