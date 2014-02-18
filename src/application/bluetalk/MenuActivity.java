package application.bluetalk;

import java.util.Locale;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import bluetooth.BluetoothService;

public class MenuActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		private ImageView m_buttonTestBluetooth;
		private ImageView m_buttonActiveBluetooth;
		private AlertDialog m_dialogForTest;
		private AlertDialog m_dialogForActive;
		private BluetoothService blueService;

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_menu_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
				return filltestView(inflater, container);
			}
			return rootView;
		}

		/**
		 * 
		 * @param inflater
		 * @param container
		 * @return
		 */
		public View filltestView(LayoutInflater inflater, ViewGroup container) {
			View testView = inflater.inflate(R.layout.fragment_menu_dummy,
					container, false);
			blueService = new BluetoothService();
			m_buttonTestBluetooth = (ImageView) testView
					.findViewById(R.id.bluetooth_send_button);
			m_buttonActiveBluetooth = (ImageView) testView.findViewById(R.id.active_bluetooth);
			m_buttonTestBluetooth.setVisibility(View.VISIBLE);
			m_buttonActiveBluetooth.setVisibility(View.VISIBLE);
			m_buttonTestBluetooth.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					validBluetooth();
				}
			});
			m_buttonActiveBluetooth.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					activeBluetooth();
				}
			});
			return testView;
		}

		private void validBluetooth() {
			m_dialogForTest = new AlertDialog.Builder(super.getActivity())
					.create();
			m_dialogForTest.setTitle("Validation Bluetooth Test");

			if (blueService.testBluetoothPresence() != false) {
				m_dialogForTest
						.setMessage("L'appareil poss�de un module Bluetooth");
				m_dialogForTest.show();
			} else {
				m_dialogForTest
						.setMessage("L'appareil ne poss�de pas de module Bluetooth");
				m_dialogForTest.show();
			}
		}

		private void activeBluetooth() {
			m_dialogForActive = new AlertDialog.Builder(super.getActivity())
					.create();
			m_dialogForActive.setTitle("Validation Bluetooth Test");
			blueService = new BluetoothService();
			if (!blueService.getAdapter().isEnabled()) {
			    Intent enableBtIntent = new Intent(blueService.getAdapter().ACTION_REQUEST_ENABLE);
			    startActivityForResult(enableBtIntent, 1);
			}
		}
	}

}
