package uni.ppp.plogocontrol;

import java.util.LinkedList;
import java.util.List;

public class LogoModel {

    private List<LogoConfig> confs;

    public LogoModel() {

        confs = new LinkedList<>();
    }

    public void addConf(LogoConfig conf) {
        this.confs.add(conf);
    }

    public void clearConfs() {
        this.confs = new LinkedList<>();
    }

    public List<LogoConfig> getConfs() {
        return this.confs;
    }

    public String toString() {

        return "";
    }
}
