package uni.ppp.plogocontrol;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the config model.
 */
public class LogoModel {

    private List<LogoConfig> configs;

    public LogoModel() {
        configs = new LinkedList<>();
    }

    public void addConfig(LogoConfig conf) {
        this.configs.add(conf);
    }

    public void clearConfigs() {
        this.configs = new LinkedList<>();
    }

    public List<LogoConfig> getConfigs() {
        return this.configs;
    }

    @Override
    public String toString() {
        return "Hallo Welt!";
    }
}
