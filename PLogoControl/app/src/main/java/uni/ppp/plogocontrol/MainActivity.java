package uni.ppp.plogocontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private final static int MY_PERMISSION_REQUEST_BLUETOOTH = 42;
    private final static int MY_PERMISSION_REQUEST_BLUETOOTH_ADMIN = 43;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mDevice;
    private EditText mMessageET;
    private Button mSendBN;

    private void findRaspberry() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            if(device.getName().equals("raspberrypi-0"))
                this.mDevice=device;
        }
    }

    private boolean hasBluetoothPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean hasBluetoothAdminPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestBluetoothPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, MY_PERMISSION_REQUEST_BLUETOOTH);

    }

    private void requestBluetoothAdminPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_ADMIN}, MY_PERMISSION_REQUEST_BLUETOOTH_ADMIN);
    }

    private void initBluetooth() {
        Log.d(TAG, "Checking Bluetooth...");
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "Device does not support Bluetooth");
            mSendBN.setClickable(false);
        } else{
            Log.d(TAG, "Bluetooth supported");
        }
        if (!mBluetoothAdapter.isEnabled()) {
            mSendBN.setClickable(false);
            Log.d(TAG, "Bluetooth not enabled");
        }
        else{
            Log.d(TAG, "Bluetooth enabled");
        }
    }

    public void onSend(View view) {
        String message = mMessageET.getText().toString();
        new MessageThread(mDevice, message).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageET = findViewById(R.id.message_et);
        mSendBN = findViewById(R.id.send_bn);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        initBluetooth();
        findRaspberry();
        if (mDevice == null) {
            Log.d(TAG, "Raspberry Pi not found");
            mSendBN.setClickable(false);
        }
    }
}
