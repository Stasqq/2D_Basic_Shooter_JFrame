package Model;

/**
 * Zmienna sluzaca do rozroznienia pociskow wystrzelonych przez bohatera i przez przeciwnikow.
 */
public enum BulletType {
    /**
     * Pocisk wystrzelony przez gracza, rani przeciwnikow.
     */
    DmgEnemy(),
    /**
     * Pocisk wystrzelony przez przeciwnika, rani gracza.
     */
    DmgPlayer();

}
