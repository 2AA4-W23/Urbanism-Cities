package ca.mcmaster.cas.se2aa4.a3.island.terrain;

public enum TileColor {
    LAND("235,223,167"), OCEAN("54,141,255"), LAGOON("129,173,125"), BEACH("245,243,152");


    public final String
            color;

    private TileColor(String color) {
        this.color = color;
    }

}
