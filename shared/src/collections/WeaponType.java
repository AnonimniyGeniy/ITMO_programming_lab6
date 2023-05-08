package collections;


/**
 * Enum for WeaponType
 */
public enum WeaponType {
    HAMMER,
    AXE,
    SHOTGUN,
    RIFLE,
    BAT;

    public static String names() {
        String names = "";
        for (WeaponType weaponType : WeaponType.values()) {
            names += weaponType.name() + ", ";
        }
        return names.substring(0, names.length() - 2);
    }
}