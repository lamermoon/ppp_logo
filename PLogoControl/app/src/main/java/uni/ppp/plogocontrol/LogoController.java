package uni.ppp.plogocontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import java.util.Set;

public class LogoController {

    private final static String TAG = "LogoController";
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice logo_rpi;


    private LogoModel model;


    // Constructor
    public LogoController() {
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        initBluetooth();
        findRaspberry();
        if (logo_rpi == null) {
            Log.d(TAG, "Raspberry Pi not found");
        }
    }



    // Bluetooth stuff to send config to RPi
    private void findRaspberry() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices)
            if(device.getName().equals("raspberrypi-0"))
                this.logo_rpi = device;
    }

    private void initBluetooth() {
        Log.d(TAG, "Checking Bluetooth...");
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "Device does not support Bluetooth");
        } else{
            Log.d(TAG, "Bluetooth supported");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Log.d(TAG, "Bluetooth not enabled");
        }
        else{
            Log.d(TAG, "Bluetooth enabled");
        }
    }

    public boolean saveConfig() {
        if (logo_rpi == null)
            return false;
        new LogoConfigSaverThread(logo_rpi, model.toString()).start();
        return true;
    }
}
