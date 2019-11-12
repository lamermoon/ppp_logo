package uni.ppp.plogocontrol;

/**
 * Abstract class representing a config for the logo
 */
public class LogoConfig {

    private LogoColor color;
    private int duration;
    private LogoConfigType type;

    public LogoConfig() {
        this.type = LogoConfigType.STATIC;
        this.duration = 1000;
        this.color = new LogoColor();
    }

    /**
     * Sets the color of the config
     * @param color the color to be set
     */
    public void setColor(LogoColor color) { this.color = color; }

    /**
     * Sets the color of the config
     * @param argb the color to be set in ARGB format
     */
    public void setColor(int argb) {
        this.color.setARGB(argb);
    }

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
     * Sets the config type
     * @param type the config type
     */
    public void setType(LogoConfigType type) {
        this.type = type;
    }

    /**
     * Gets the config type
     * @return the config's type
     */
    public LogoConfigType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "(" + this.type + "," + this.color + "," + this.duration + ")";
    }
}
