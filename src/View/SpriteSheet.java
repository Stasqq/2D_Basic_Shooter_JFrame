package View;

import java.awt.image.BufferedImage;

/**
 * Reprezentacja duzego pliku graficznego, z ktorego nastepnie mozna wycinac poszczegolne fragmenty.
 * Fragmenty te sa interpretowane jako kolumny i wiersze.
 * Kazda kolumna i rzad maja po 32 piksele.
 */
public class SpriteSheet {
    /**
     * Plik graficzny
     */
    private BufferedImage image;

    /**
     * Reprezentacja duzego pliku graficznego, z ktorego nastepnie mozna wycinac poszczegolne fragmenty.
     *
     * @param image Plik graficzny
     */
    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }

    /**
     * Wyciecie porzadanego fragmentu obrazka.
     *
     * @param col    Numer kolumny(numeracja od 1)
     * @param row    Numer wierszu(numeracja od 1)
     * @param width  Szerokosc jaka wyciac
     * @param height Wysokosc jaka wyciac
     * @return Wyciety plik graficzny
     */
    public BufferedImage grabImage(int col, int row, int width, int height) {
        return image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
    }
}
