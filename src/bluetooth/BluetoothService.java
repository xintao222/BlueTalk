package bluetooth;

import android.bluetooth.BluetoothAdapter;

public class BluetoothService {

	BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
	/**
	 * Constructor for BluetoothService
	 */
	public BluetoothService(){
		
	}
	
	/**
	 * Getter of Adapter
	 */
	public BluetoothAdapter getAdapter(){
		return blueAdapter;
	}
	
	/**
	 * Testing presence of Bluetooth on Device
	 */
	public boolean testBluetoothPresence(){
		if (blueAdapter == null) {
		    return false;
		}else {
			return true;
		}
	}
}
