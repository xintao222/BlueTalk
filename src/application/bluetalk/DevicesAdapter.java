package application.bluetalk;

import java.util.List;
import java.util.Set;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DevicesAdapter extends BaseAdapter {
	private Set<BluetoothDevice> mListDevice;
	private Context mContext;
	private LayoutInflater mInflater;
	
	public DevicesAdapter(Context context, Set<BluetoothDevice> aListDevice){
		  mContext = context;
		  mListDevice = aListDevice;
		  mInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return mListDevice.size();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		  LinearLayout layoutItem;
		  //(1) : Réutilisation des layouts
		  if (convertView == null) {
		  	//Initialisation de notre item à partir du  layout XML "personne_layout.xml"
		    layoutItem = (LinearLayout) mInflater.inflate(R.layout.listelement, parent, false);
		  } else {
		  	layoutItem = (LinearLayout) convertView;
		  }
		  
		  //(2) : Récupération des TextView de notre layout      
		  TextView name = (TextView)layoutItem.findViewById(R.id.DeviceName);
		  
		  //(3) : Renseignement des valeurs       
		  //name.setText();
		  
		  return layoutItem;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

}
