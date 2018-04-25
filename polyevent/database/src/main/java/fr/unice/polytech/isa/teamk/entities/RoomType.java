package fr.unice.polytech.isa.teamk.entities;

public enum RoomType {

    AMPHI, COURS, TD, UNKNOWN;

    public static RoomType convert(int value) {
        switch (value) {
            case 0:
                return RoomType.AMPHI;
            case 1:
                return RoomType.COURS;
            case 2:
                return RoomType.TD;
            default:
                return RoomType.UNKNOWN;
        }
    }

}
