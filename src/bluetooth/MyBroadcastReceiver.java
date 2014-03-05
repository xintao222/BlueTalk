package bluetooth;

import java.util.ArrayList;

import android.R;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyBroadcastReceiver extends BroadcastReceiver{

	private final ArrayList<String> arr = new ArrayList<String>();
	
	@Override
	public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // Quand la recherche trouve un terminal
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            // On récupère l'object BluetoothDevice depuis l'Intent
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            // On ajoute le nom et l'adresse du périphérique dans un ArrayAdapter (par exemple pour l'afficher dans une ListView)
            String derp = device.getName() + " - " + device.getAddress();
            arr.add(derp);
        }
	}
	
	public final ArrayList<String> toto(){
		return arr;
	}
	
}
