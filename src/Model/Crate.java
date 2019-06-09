package Model;

import View.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Reprezentacja paczki z amunicja.
 */
public class Crate extends GameObject {
    /**
     * Grafika wyswietlana przed metode render.
     */
    protected BufferedImage crateImage;

    /**
     * Reprezentacja paczki z amunicja.
     *
     * @param x  Wspolrzedna x polozenia
     * @param y  Wspolrzedna y polozenia
     * @param id Rodzaj obiektu
     * @param ss Odnoscik do obiektu przechowujacego plik graficzny, z ktorego potem rysujemy nasz obiekt
     */
    public Crate(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);

        crateImage = ss.grabImage(3, 1, 32, 32);
    }

    /**
     * Metoda uaktualniajaca, w tym przypadku pusta
     */
    public void tick() {

    }

    /**
     * Metoda rysujaca skrzynie.
     *
     * @param g Odnosnik do miejsca w ktorym mamy narysowac obiekt
     */
    public void render(Graphics g) {
        g.drawImage(crateImage, x, y, null);
    }

    /**
     * Zwraca prostokat bedacy obrysem obiektu
     *
     * @return Obrys skrzyni
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
