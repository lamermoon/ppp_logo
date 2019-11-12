package uni.ppp.plogocontrol;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the config model.
 */
public class LogoModel {

    private static String TAG = "LogoModel";
    private List<LogoConfig> configs;

    public LogoModel() {
        configs = new LinkedList<>();
    }

    public void addConfig(LogoConfig conf) {
        this.configs.add(conf);
        Log.d(TAG, "Config added.");
        Log.d(TAG, "New config length: " + configs.size());
    }

    public void clearConfigs() {
        this.configs = new LinkedList<>();
    }

    public List<LogoConfig> getConfigs() {
        return this.configs;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (LogoConfig conf: this.configs) {
            sb.append(conf.toString());
        }
        return sb.toString();
    }
}
