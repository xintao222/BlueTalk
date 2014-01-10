package bluetooth;

import java.io.IOException;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class BluetoothServer {
    private final BluetoothServerSocket m_blueServerSocket;
    private BluetoothService m_blueService;

    /*
     * Constructor of BluetoothServer
     */
    public BluetoothServer() {
        // On utilise un objet temporaire qui sera assigné plus tard à blueServerSocket car blueServerSocket est "final"
        BluetoothServerSocket tmp = null;
        try {
            // MON_UUID est l'UUID (comprenez identifiant serveur) de l'application. Cette valeur est nécessaire côté client également !
            tmp = m_blueService.getAdapter().listenUsingRfcommWithServiceRecord("Test", null);
        } catch (IOException e) { }
        m_blueServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket blueSocket = null;
        // On attend une erreur ou une connexion entrante
        while (true) {
            try {
                blueSocket = m_blueServerSocket.accept();
            } catch (IOException e) {
                break;
            }
            // Si une connexion est acceptée
            if (blueSocket != null) {
                // On fait ce qu'on veut de la connexion (dans un thread séparé), à vous de la créer
                manageConnectedSocket(blueSocket);
                try {
					m_blueServerSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                break;
            }
        }
    }

    private void manageConnectedSocket(BluetoothSocket blueSocket) {
		// TODO Auto-generated method stub
		
	}

	// On stoppe l'écoute des connexions et on tue le thread
    public void cancel() {
        try {
            m_blueServerSocket.close();
        } catch (IOException e) { }
    }
}
