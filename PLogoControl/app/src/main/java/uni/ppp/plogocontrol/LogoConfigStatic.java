package uni.ppp.plogocontrol;

public class LogoConfigStatic implements LogoConfig {

    private LogoConfigType type = LogoConfigType.STATIC;
    private LogoColor color;
    private int duration;

    @Override
    public void setColor(LogoColor color) {
        this.color = color;
    }

    @Override
    public void setDuration(int ms) {
        this.duration = ms;
    }
}
