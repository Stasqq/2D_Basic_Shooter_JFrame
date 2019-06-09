package Model;

import View.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Reprezentacja bloku sciany, kolizje z nia uniemozliwiaja przejscie oraz niszcza trafiane pociski.
 */
public class Block extends GameObject {
    /**
     * Grafika bloku
     */
    private BufferedImage block_image;

    /**
     * Reprezentacja bloku sciany, kolizje z nia uniemozliwiaja przejscie oraz niszcza trafiane pociski.
     *
     * @param x  Wspolrzedna x bloku
     * @param y  Wspolrzedna y bloku
     * @param id Rodzaj obiektu
     * @param ss Odnoscik do obiektu przechowujacego plik graficzny, z ktorego potem rysujemy nasz obiekt
     */
    public Block(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);

        block_image = ss.grabImage(2, 2, 32, 32);
    }

    /**
     * Metoda uaktualniajaca, w tym przypadku pusta
     */
    public void tick() {

    }

    /**
     * Metoda rysujaca blok.
     *
     * @param g Odnosnik do miejsca w ktorym mamy narysowac obiekt
     */
    public void render(Graphics g) {
        g.drawImage(block_image, x, y, null);
    }

    /**
     * Zwraca prostokat bedacy obrysem obiektu
     *
     * @return Obrys bloku
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
