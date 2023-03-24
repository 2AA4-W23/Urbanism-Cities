package ca.mcmaster.cas.se2aa4.a3.island.terrain;

public enum TileColor {
    LAND("235,223,167"), OCEAN("54,141,255"), LAGOON("129,173,125"), BEACH("245,243,152"),
    ARCTIC("224,224,224"), DESERT("179,145,46"), TROPICAL("59,89,37"), LAKE("19,114,148"),
    MUDDY("125,85,25"), ROCKY("130,130,130"), TAIGA("9,92,0"), SAVANNAH("255,196,0"), GLACIER("156,242,255");


    public final String
            color;

    TileColor(String color) {
        this.color = color;
    }

}
