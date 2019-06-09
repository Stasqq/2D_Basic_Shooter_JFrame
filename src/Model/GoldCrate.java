package Model;

import View.SpriteSheet;

/**
 * Reprezentacja paczki z punktami/zlotem.
 * Paczki wypadaja po zabiciu przeciwnika.
 */
public class GoldCrate extends Crate {
    /**
     * Wartosc punktow paczki
     */
    private int value;

    /**
     * Reprezentacja paczki z punktami/zlotem.
     *
     * @param x         Wspolrzedna x polozenia
     * @param y         Wspolrzedna y polozenia
     * @param id        Rodzaj obiektu
     * @param ss        Odnoscik do obiektu przechowujacego plik graficzny, z ktorego potem rysujemy nasz obiekt
     * @param gold_type Zmienna ustawiajaca wartosc wartosc paczki (ostateczna wartosc to gold_type*gold_type*10)
     */
    public GoldCrate(int x, int y, ID id, SpriteSheet ss, int gold_type) {
        super(x, y, id, ss);

        value = gold_type * gold_type * 10;

        crateImage = ss.grabImage(gold_type, 3, 32, 32);
    }

    /**
     * Metoda zwracajaca punktowa wartosc paczki
     *
     * @return Wartosc punktow paczki
     */
    public int getValue() {
        return value;
    }
}
