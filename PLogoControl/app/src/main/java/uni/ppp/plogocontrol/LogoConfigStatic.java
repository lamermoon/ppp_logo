package uni.ppp.plogocontrol;

public class LogoConfigStatic extends LogoConfig {

    public LogoConfigStatic() {
        this.color = new LogoColor();
        this.duration = 10;
    }

    @Override
    public LogoConfigType getType() {
        return LogoConfigType.STATIC;
    }
}
