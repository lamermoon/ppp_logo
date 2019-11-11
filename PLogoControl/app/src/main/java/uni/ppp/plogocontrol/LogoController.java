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
        this.model = new LogoModel();
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        initBluetooth();
        findRaspberry();
        if (logo_rpi == null) {
            Log.d(TAG, "Raspberry Pi not found");
        }
    }

    public void onNewConf(LogoConfigType t) {
        LogoConfig conf;
        switch (t) {
            //case PULSING: conf = new LogoConfigPulsing(); break;
            //case RANDOM: conf = new LogoConfigRandom(); break;
            default: conf = new LogoConfigStatic(); break;
        }
        this.model.addConf(conf);
    }

    public void onClearConfs() {
        this.model.clearConfs();
    }



    // Bluetooth stuff to send config to RPi
    private void findRaspberry() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            Log.d(TAG, "Paired device: " + device.getName() + "(" + device.getAddress() + ")");
            if (device.getName().equals("ppplogo"))
                this.logo_rpi = device;
        }
    }

    private void initBluetooth() {
        Log.d(TAG, "Checking Bluetooth...");
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "Device does not support Bluetooth");
        } else {
            Log.d(TAG, "Bluetooth supported");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Log.d(TAG, "Bluetooth not enabled");
        }
        else {
            Log.d(TAG, "Bluetooth enabled");
        }
    }

    public boolean saveConfig() {
        if (logo_rpi == null || !mBluetoothAdapter.isEnabled())
            return false;
        String config_str = "Hallo RPi!"; //model.toString();
        new LogoConfigSaverThread(logo_rpi, config_str).start();
        return true;
    }
}
