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

public class MessageThread extends Thread {
    private final static String TAG="MessageThread";
    private final static String MY_UUID ="00001101-0000-1000-8000-00805f9b34fb";
    private BluetoothSocket mSocket=null;
    private String mMessage;


    public MessageThread(BluetoothDevice device, String message) {
        Log.d(TAG,"Trying to send message...");
        this.mMessage=message;
        try {
            UUID uuid = UUID.fromString(MY_UUID);
            mSocket = device.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void manageConnectedSocket(BluetoothSocket socket) throws IOException {
        Log.d(TAG,"Connection successful");
        OutputStream os=socket.getOutputStream();
        PrintStream sender = new PrintStream(os);
        sender.print(mMessage);
        Log.d(TAG,"Message sent");
        InputStream is=socket.getInputStream();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is));
        Log.d(TAG,"Received: " + reader.readLine());
    }

    public void run() {
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        try {
            mSocket.connect();
            manageConnectedSocket(mSocket);
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
