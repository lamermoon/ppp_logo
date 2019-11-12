package uni.ppp.plogocontrol;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.larswerkman.holocolorpicker.ColorPicker;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The app's main activity
 */
public class MainActivity extends AppCompatActivity implements ColorPicker.OnColorChangedListener {

    private final static String TAG = "MainActivity";

    private LogoController logo;

    /**
     * Callback function that is executed when the color of the color wheel changes
     * @param color
     */
    public void onColorChanged(int color) {
        LogoColor c = new LogoColor();
        c.setARGB(color);
    }

    /**
     * Callback function to be executed when the button for saving the config to the logo device is pressed
     * @param view the view that triggered this call
     */
    public void onSend(View view) {
        this.logo.saveConfig();
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
