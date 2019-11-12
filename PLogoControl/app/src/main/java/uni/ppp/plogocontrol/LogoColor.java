package uni.ppp.plogocontrol;

/**
 * This class represents a color that can be used in a logo config.
 * It provides methods to easily convert (A)RGB values to separate integers and back.
 */
class LogoColor {

    private final static int aoff = 24;
    private final static int roff = 16;
    private final static int goff = 8;
    private final static int boff = 0;
    private final static int mask = 0xFF;

    private int a;
    private int r;
    private int g;
    private int b;

    /**
     * Instantiates the P++ gold color
     */
    LogoColor() {
        this.a = 0xFF;
        this.r = 0xCF;
        this.g = 0xA5;
        this.b = 0x44;
    }

    /**
     * Instantiates a color object with the given RGB values
     *
     * @param r the color's red byte
     * @param g the color's green byte
     * @param b the color's blue byte
     */
    LogoColor(int r, int g, int b) {
        this(0xFF, r, g, b);
    }

    /**
     * Instantiates a color object with the given ARGB values
     *
     * @param a the color's alpha channel byte
     * @param r the color's red byte
     * @param g the color's green byte
     * @param b the color's blue byte
     */
    LogoColor(int a, int r, int g, int b) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Instantiates a color object with the given ARGB value
     *
     * @param argb a 4 byte integer representing the a, r, g, and b component respectively
     */
    LogoColor(int argb) {
        setARGB(argb);
    }

    /**
     * Sets the RGB components of the color
     * @param rgb a 4 byte integer where the most significant byte is ignored and the others are treated as RGB bytes
     */
    void setRGB(int rgb) {
        this.r = (rgb >> roff) & mask;
        this.g = (rgb >> goff) & mask;
        this.b = (rgb >> boff) & mask;
    }

    /**
     * Sets the ARGB components of the color
     * @param argb a 4 byte integer representing the ARGB components
     */
    void setARGB(int argb) {
        this.setRGB(argb);
        this.a = (argb >> aoff) & mask;
    }

    /**
     * Gets the color's alpha channel
     * @return the alpha channel's value
     */
    int getA() {
        return this.a;
    }

    /**
     * Gets the color's red component
     * @return the color's red component
     */
    int getR() {
        return this.r;
    }

    /**
     * Gets the color's green component
     * @return the color's green component
     */
    int getG() {
        return this.g;
    }

    /**
     * Gets the color's blue component
     * @return the color's blue component
     */
    int getB() {
        return this.b;
    }

    /**
     * Gets the color's RGB values in one integer
     * @return an integer representing the RGB value
     */
    int getRGB() {
        return (this.getR() << roff) | (this.getG() << goff) | (this.getB() << boff);
    }

    /**
     * Gets the color's ARGB values in one integer
     * @return an integer representing the ARGB values
     */
    int getARGB() {
        return (this.a << aoff) | this.getRGB();
    }
}
