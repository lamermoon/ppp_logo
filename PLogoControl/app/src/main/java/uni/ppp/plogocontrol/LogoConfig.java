package uni.ppp.plogocontrol;

/**
 * Abstract class representing a config for the logo
 */
public abstract class LogoConfig {

    protected LogoColor color;
    protected int duration;

    /**
     * Sets the color of the config
     * @param color the color to be set
     */
    public void setColor(LogoColor color) { this.color = color; }

    /**
     * Gets the configured color
     * @return the configured color
     */
    public LogoColor getColor() { return this.color; }

    /**
     * Sets the duration
     * @param ms the duration in milliseconds
     */
    public void setDuration(int ms) { this.duration = ms; }

    /**
     * Gets the configured duration
     * @return the configured duration in milliseconds
     */
    public int getDuration() { return this.duration; }

    /**
     * Gets the config type
     * @return the config's type
     */
    public abstract LogoConfigType getType();
}
