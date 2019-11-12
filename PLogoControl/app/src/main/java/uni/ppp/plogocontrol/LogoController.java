package uni.ppp.plogocontrol;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Set;

public class LogoController {

    private final static String TAG = "LogoController";
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothDevice logo_rpi;

    private LogoModel model;


    // Constructor
    public LogoController(Context context) {
        this.model = new LogoModel();

        BluetoothManager manager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        this.mBluetoothAdapter = manager.getAdapter();

        initBluetooth();

        if(mBluetoothAdapter != null){
            findRaspberry();
        }else{
            showWarningView(context);
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
            if (device.getName().equals("ppplogo")) {
                this.logo_rpi = device;
            }
        }

        if (logo_rpi == null) {
            Log.d(TAG, "Raspberry Pi not found");
        }
    }

    private void initBluetooth() {
        Log.d(TAG, "Checking Bluetooth...");
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "Device does not support Bluetooth");
            //verhindert Abstürtze bei nicht vorhandenem Bluetooth. Sonst nullpointer bei mBluetoothAdapter.isEnabled()
            return;
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

    /**
     * Zeigt eine Warnung an sodass der User weiß wo das Problem liegt.
     * MVC ist in Android leider nicht gut voneinander abgegrenzt, deswegen kann man dies nicht wirklich gut anders lösen.
     * Wer (bessere!) Alternativen hat kann dies gerne umsetzen.
     * @param context
     */
    private void showWarningView(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main_no_bluetooth, null);
        ((Activity)context).setContentView(view);
    }
}
