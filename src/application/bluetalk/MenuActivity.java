package application.bluetalk;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bluetooth.BluetoothService;
import bluetooth.MyBroadcastReceiver;

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
	private BluetoothService blueService;
	private BroadcastReceiver receiver;
	private BluetoothAdapter bAdapt;
	
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
	
	
	@Override
	/**
	 * Methode d'action sur les options du menu
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			blueService = new BluetoothService();
			if (!blueService.getAdapter().isEnabled()) {
				Intent enableBtIntent = new Intent(
						blueService.getAdapter().ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBtIntent, 1);
			}
			break;
		case R.id.action_detection:
			//blueService = new BluetoothService();
			bAdapt = BluetoothAdapter.getDefaultAdapter();
			//Set<BluetoothDevice> paireDevices = bAdapt.getBondedDevices();
			if(bAdapt.isDiscovering()){
				bAdapt.cancelDiscovery();
				unregisterReceiver(receiver);
			}
			bAdapt.startDiscovery();
			receiver = new MyBroadcastReceiver();
			IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
			this.registerReceiver(receiver, filter);
			ListView lv = (ListView)findViewById(R.id.listDevice);
			lv.setAdapter(new ArrayAdapter<String>(this, android.R.id.list, new ArrayList<String>()));
			lv.setAdapter(new ArrayAdapter<String>(this, android.R.id.list, receiver.getResultData()));
			break;
		}
		return true;
	}

	@Override
	protected void onDestroy() {
		bAdapt.cancelDiscovery();
		unregisterReceiver(receiver);
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
		private ImageView m_buttonActiveBluetooth;
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
			//CODE DE CREATION D'UN BOUTON
//			blueService = new BluetoothService();
			//Récupration de l'élément graphique du layout xml
//			m_buttonActiveBluetooth = (ImageView) testView
//					.findViewById(R.id.active_bluetooth);
			//Rendre visible (optionnel)
//			m_buttonActiveBluetooth.setVisibility(View.VISIBLE);
			//Mise en place de l'action en cas de clic et appel de la méthode citée plus bas
//			m_buttonActiveBluetooth.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					activeBluetooth();
//				}
//			});
			return testView;
		}
	}

}
