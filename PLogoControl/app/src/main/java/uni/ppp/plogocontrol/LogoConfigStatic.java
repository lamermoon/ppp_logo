package uni.ppp.plogocontrol;

/**
 * Implementation of a LogoConfig.
 *
 * This config is used to configure a static color on the logo device
 */
public class LogoConfigStatic extends LogoConfig {

    /**
     * The constructor instantiates the config with the P++ gold color and a duration of 1 second
     */
    public LogoConfigStatic() {
        this.color = new LogoColor();
        this.duration = 1000;
    }

    @Override
    public LogoConfigType getType() {
        return LogoConfigType.STATIC;
    }
}
