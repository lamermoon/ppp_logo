package uni.ppp.plogocontrol;

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

    void setRGB(int rgb) {
        this.r = (rgb >> roff) & mask;
        this.g = (rgb >> goff) & mask;
        this.b = (rgb >> boff) & mask;
    }

    void setARGB(int argb) {
        this.setRGB(argb);
        this.a = (argb >> aoff) & mask;
    }

    int getA() {
        return this.a;
    }

    int getR() {
        return this.r;
    }
    int getG() {
        return this.g;
    }

    int getB() {
        return this.b;
    }

    int getRGB() {
        return (this.getR() << roff) | (this.getG() << goff) | (this.getB() << boff);
    }

    int getARGB() {
        return (this.a << aoff) | this.getRGB();
    }
}
