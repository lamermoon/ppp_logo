package uni.ppp.plogocontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

/**
 * The app's main activity
 */
public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    private LogoController logo;


    /**
     * Callback function to be executed when the button for saving the config to the logo device is pressed
     * @param view the view that triggered this call
     */
    public void onSend(View view) {
        this.logo.sendConfigToLogoDevice();
    }

    /**
     * Callback function to be executed when the button for adding a new config is pressed
     * @param view the view that triggered this call
     */
    public void onNewConfig(View view) {
        Intent intent = new Intent(this, AddConfigActivity.class);
        startActivity(intent);
    }

    /**
     * Shows a warning that no Bluetooth support was found on the smartphone.
     */
    public void showWarningView() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main_no_bluetooth, null);
        setContentView(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.logo = new LogoController(this);
    }
}
