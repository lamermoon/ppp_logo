package uni.ppp.plogocontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class LogoConfigSaverThread extends Thread {
    private final static String TAG = "LogoConfigSaverThread";
    private final static String MY_UUID = "94f39d29-7d6d-437d-973b-fba39e49d4ee";
    private BluetoothSocket mSocket = null;
    private String mMessage;


    public LogoConfigSaverThread(BluetoothDevice device, String message) {
        Log.d(TAG,"Trying to send message...");
        this.mMessage = message;
        UUID uuid = UUID.fromString(MY_UUID);
        try {
            mSocket = device.createRfcommSocketToServiceRecord(uuid);
            Log.d(TAG, "Rfcomm socket created.");
        } catch (IOException e) {
            Log.d(TAG, "Error creating Rfcomm socket!" + e.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void manageConnectedSocket(BluetoothSocket socket) throws IOException {
        OutputStream os = socket.getOutputStream();
        PrintStream sender = new PrintStream(os);
        sender.print(mMessage);
        Log.d(TAG,"Message sent");
        InputStream is = socket.getInputStream();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is));
        Log.d(TAG,"Received: " + reader.readLine());
    }

    public void run() {
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        for (int i = 0; i < 5; ++i) {
            try {
                mSocket.connect();
                Log.d(TAG, "Socket connected.");
                break;
            } catch (IOException e) {
                Log.d(TAG, "Connecting socked failed! " + ((i < 4) ?  "Retrying..." : "Stopping!"));
                if (i == 4)
                    return;
            }
        }
        try {
            manageConnectedSocket(mSocket);
            Log.d(TAG, "Config transmitted successfully");
        } catch (IOException e) {
            Log.d(TAG, "Config transmission failed!");
        }
        try {
            mSocket.close();
            Log.d(TAG, "Socket closed.");
        } catch (IOException e) {
            Log.d(TAG, "Closing socket failed!");
        }
    }
}
