package uni.ppp.plogocontrol;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.larswerkman.holocolorpicker.ColorPicker;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ColorPicker.OnColorChangedListener {

    private final static String TAG = "MainActivity";

    private LogoController logo;

    public void onColorChanged(int color) {
        LogoColor c = new LogoColor();
        c.setARGB(color);
    }

    public void onSend(View view) {
        logo.saveConfig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.logo = new LogoController();
    }
}
