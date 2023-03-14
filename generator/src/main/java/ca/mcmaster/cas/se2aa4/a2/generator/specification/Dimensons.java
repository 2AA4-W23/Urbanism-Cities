package ca.mcmaster.cas.se2aa4.a2.generator.specification;

public class Dimensons {

    private static int height, width;

    public Dimensons() {

    }

    public Dimensons(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int height() {
        return this.height;
    }

    public int width() {
        return this.width;
    }

}
