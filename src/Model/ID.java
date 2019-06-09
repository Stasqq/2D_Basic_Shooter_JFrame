package Model;

/**
 * Zmienna identyfikujaca rodzaj obiektu w grze.
 * Potrzebna do przegladania kontenera obiektow.
 */
public enum ID {
    /**
     * Bohater
     */
    Player(),
    /**
     * Blok sciany
     */
    Block(),
    /**
     * Pudlo z amunicja
     */
    Create(),
    /**
     * Pocisk
     */
    Bullet(),
    /**
     * Pudlo z punktami/zlotem
     */
    GoldCrate(),
    /**
     * Przeciwnik
     */
    Enemy();
}
