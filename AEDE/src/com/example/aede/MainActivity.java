package com.example.aede;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "POST", "MEMBER", "COMPANY" };

	// private int[] tabs = {R.drawable.icon_post, R.drawable.icon_member,
	// R.drawable.icon_company};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);

		mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), this);

		viewPager.setAdapter(mAdapter);
		actionBar = getActionBar();
		// actionBar.setDisplayShowTitleEnabled(false);
		// actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		/*
		 * for (String tab_name : tabs) { Tab tab = actionBar.newTab();
		 * //tab.setIcon(R.drawable.ic_launcher); tab.setText(tab_name);
		 * tab.setTabListener(this); actionBar.addTab(tab); }
		 */
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Search");
		menu.add(1, 1, 0, "Log in");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// สร้างเงื่อนไขเพื่อเช็คดูว่า menu ไหนที่ถูกเลือกเพื่อทำการเขียน
		// process ต่อไปโดยในการเช็คว่า menu ไหนถูกเลือกนั้นจะใช้คำสั่ง
		// .getItemId
		int id = item.getItemId();// getItemId()เรียกใช้ได้เลยอยู่ใน
									// Menuitem.class จากไลบารี่
		if (id == 0) {
			// Search
			Intent intent = new Intent(MainActivity.this, Search.class);
			startActivity(intent);
		} else if (id == 1) {
			// Login
			Intent intent = new Intent(MainActivity.this, Login.class);
			startActivity(intent);
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}
