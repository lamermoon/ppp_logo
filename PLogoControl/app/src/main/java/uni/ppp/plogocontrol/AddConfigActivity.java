package uni.ppp.plogocontrol;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity adds a config
 */
public class AddConfigActivity extends AppCompatActivity {

    public static String TAG = "AddConfigActivity";
    LogoController logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        this.logo = new LogoController();
        this.logo.prepareNewConfig();

        Spinner type = findViewById(R.id.add_config_type_dropdown);
        String[] items = new String[] {
                "STATIC",
                "PULSING",
                "RANDOM"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, items);
        type.setAdapter(adapter);
        type.setOnItemSelectedListener(logo);

        ColorPicker picker = findViewById(R.id.picker);
        SVBar svBar = findViewById(R.id.svbar);
        SaturationBar saturationBar = findViewById(R.id.saturationbar);
        ValueBar valueBar = findViewById(R.id.valuebar);
        picker.addSVBar(svBar);
        picker.addSaturationBar(saturationBar);
        picker.addValueBar(valueBar);
        picker.setOldCenterColor(picker.getColor());
        picker.setShowOldCenterColor(true);
    }

    public void onSaveConfig(View view) {
        EditText duration = findViewById(R.id.add_config_duration_input);

        logo.saveTmpConfig(Integer.parseInt(duration.getText().toString()));
        Log.d(TAG, "Temp config saved.");
    }
}