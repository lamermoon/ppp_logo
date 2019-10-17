package uni.ppp.plogocontrol;

public abstract class LogoConfig {

    protected LogoColor color;
    protected int duration;

    public void setColor(LogoColor color) { this.color = color; }
    public LogoColor getColor() { return this.color; }
    public void setDuration(int ms) { this.duration = ms; }
    public int getDuration() { return this.duration; }
    public abstract LogoConfigType getType();
}
