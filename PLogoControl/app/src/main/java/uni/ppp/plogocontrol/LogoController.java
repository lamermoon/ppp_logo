package uni.ppp.plogocontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.larswerkman.holocolorpicker.ColorPicker;

import java.util.Set;

/**
 * This class represents the app controller.
 * It manages the config and view.
 */
public class LogoController implements ColorPicker.OnColorSelectedListener, AdapterView.OnItemSelectedListener {

    private final static String TAG = "LogoController";
    private static BluetoothAdapter mBluetoothAdapter = null;
    private static BluetoothDevice logo_device = null;

    private static LogoModel model = null;
    private LogoConfig tmp_config;


    /**
     * Constructor for a new LogoController object
     * Used by any other activity
     */
    public LogoController() {

    }

    /**
     * Constructor for a new LogoController object
     * Used by the MainActivity
     *
     * @param view the main activity view to show a possible warning about missing Bluetooth capabilities
     */
    public LogoController(MainActivity view) {
        if (model == null)
            this.model = new LogoModel();
        if (mBluetoothAdapter == null)
            this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        initBluetooth();

        if (mBluetoothAdapter != null){
            findLogoDevice();
        } else {
            view.showWarningView();
        }
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
        if (logo_device == null) {
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            for (BluetoothDevice device : pairedDevices) {
                Log.d(TAG, "Paired device: " + device.getName() + "(" + device.getAddress() + ")");
                if (device.getName().equals("ppplogo")) {
                    this.logo_device = device;
                }
            }

            if (logo_device == null) {
                Log.d(TAG, "Raspberry Pi not found");
            }
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
    public boolean sendConfigToLogoDevice() {
        String config_str = model.toString();
        Log.d(TAG, "Config length: " + config_str.length() + " Config: " + config_str);
        if (logo_device == null || !mBluetoothAdapter.isEnabled())
            return false;
        new LogoConfigSaverThread(logo_device, config_str).start();
        return true;
    }

    /**
     * Prepares a new LogoConfig object
     */
    public void prepareNewConfig() {
        tmp_config = new LogoConfig();
    }

    /**
     * Saves the temporal config to the model
     */
    public void saveTmpConfig(int duration) {
        tmp_config.setDuration(duration);
        model.addConfig(tmp_config);
        tmp_config = new LogoConfig();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tmp_config.setType(LogoConfigType.valueOf((String)parent.getItemAtPosition(position)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onColorSelected(int color) {
        tmp_config.setColor(color);
        Log.d(TAG, "Color set in config.");
    }
}
