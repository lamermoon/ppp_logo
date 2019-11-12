package uni.ppp.plogocontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

import java.util.Set;

/**
 * This class represents the app controller.
 * It manages the config and view.
 */
public class LogoController {

    private final static String TAG = "LogoController";
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice logo_rpi;

    private LogoModel model;


    /**
     * Constructor for a new LogoController object
     *
     * @param view the main activity view to show a possible warning about missing Bluetooth capabilities
     */
    public LogoController(MainActivity view) {
        this.model = new LogoModel();
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        initBluetooth();

        if (mBluetoothAdapter != null){
            findLogoDevice();
        } else {
            view.showWarningView();
        }
    }

    public void onNewConf(LogoConfigType t) {
        LogoConfig conf;
        switch (t) {
            //case PULSING: conf = new LogoConfigPulsing(); break;
            //case RANDOM: conf = new LogoConfigRandom(); break;
            default: conf = new LogoConfigStatic(); break;
        }
        this.model.addConfig(conf);
    }

    public void onClearConfs() {
        this.model.clearConfigs();
    }


    /**
     * Scans the paired Bluetooth devices for a device named "ppplogo".
     *
     * NOTE: The user has to pair the device manually for now.
     * A later version of this method will scan for the logo device and automatically pair it, before continuing
     *
     * TODO: Remove hardcoding of "ppplogo" device name and let the user choose
     * TODO: Automatically pair
     */
    private void findLogoDevice() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            Log.d(TAG, "Paired device: " + device.getName() + "(" + device.getAddress() + ")");
            if (device.getName().equals("ppplogo")) {
                this.logo_rpi = device;
            }
        }

        if (logo_rpi == null) {
            Log.d(TAG, "Raspberry Pi not found");
        }
    }

    /**
     * Initiates Bluetooth
     *
     * TODO: Enable Bluetooth if it is disabled instead of just logging that it is disabled
     */
    private void initBluetooth() {
        Log.d(TAG, "Checking Bluetooth...");
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "Device does not support Bluetooth");
        }
        else {
            Log.d(TAG, "Bluetooth supported");

            if (!mBluetoothAdapter.isEnabled()) {
                Log.d(TAG, "Bluetooth not enabled");
            }
            else {
                Log.d(TAG, "Bluetooth enabled");
            }
        }
    }

    /**
     * Sends the config to the logo and enables it.
     *
     * @return <code>true</code> if save was successful and <code>false</code> otherwise
     */
    public boolean saveConfig() {
        if (logo_rpi == null || !mBluetoothAdapter.isEnabled())
            return false;
        String config_str = model.toString();
        new LogoConfigSaverThread(logo_rpi, config_str).start();
        return true;
    }
}
