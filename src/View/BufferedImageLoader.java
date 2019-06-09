package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Obiekt wczytujacy pliki graficzne z podanej sciezki
 */
public class BufferedImageLoader {
    /**
     * Plik graficzny
     */
    private BufferedImage image;

    /**
     * Metoda wczytujaca pliki graficzne do zmiennych
     *
     * @param path Sciezka do pliku
     * @return Zaladowany plik graficzny
     */
    public BufferedImage loadImage(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
