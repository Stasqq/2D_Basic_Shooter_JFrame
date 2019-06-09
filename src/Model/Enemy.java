package Model;

import View.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Reprezentacja haotycznie poruszajacych sie przeciwnikow. Kolizje z nimi odejmuja punkty zycia bohaterowi.
 */
public class Enemy extends GameObject {
    /**
     * Przechowuje obiekty gry
     */
    private Handler handler;
    /**
     * Zmienna losowa
     */
    private Random r = new Random();
    /**
     * Zmienna ktorej wartosc decyduje o zmianie kierunku poruszania sie przeciwnika
     */
    private int choose = 0;
    /**
     * Punkty zycia
     */
    private int hp = 100;
    /**
     * Grafika wyswietlana przed metode render
     */
    private BufferedImage enemy_image;

    /**
     * Reprezentacja haotycznie poruszajacych sie przeciwnikow. Kolizje z nimi odejmuja punkty zycia bohaterowi.
     *
     * @param x       Wspolrzedna x polozenia
     * @param y       Wspolrzedna y polozenia
     * @param id      Rodzaj obiektu
     * @param handler Odnosnik do kontenera z obiektami gry
     * @param ss      Odnoscik do obiektu przechowujacego plik graficzny, z ktorego potem rysujemy nasz obiekt
     */
    public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        enemy_image = ss.grabImage(3, 2, 32, 32);
    }

    /**
     * Uaktualnienie stanu obiektu.
     * Tutaj: zmiana polozenia obiektu, sprawdzenie kolizji z blokami(jesli tak zmiana kierunku poruszania), sprawdzenie kolizji z pociskami gracza(jesli tak odjecie punktow zycia).
     */
    public void tick() {
        x += velX;
        y += velY;

        choose = r.nextInt(10);

        for (int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject = handler.getObject().get(i);

            if (tempObject.getId() == ID.Block) {
                if (getBounds2().intersects(tempObject.getBounds())) {
                    x += (velX * 3) * -1;
                    y += (velY * 3) * -1;
                    velX *= -1;
                    velY *= -1;
                } else if (choose == 0) {
                    velX = (r.nextInt(4 - -4) + -4);
                    velY = (r.nextInt(4 - -4) + -4);
                }
            }

            if (tempObject.getId() == ID.Bullet) {
                Bullet bullet = (Bullet) tempObject;
                if (getBounds().intersects(tempObject.getBounds()) && bullet.getBt() == BulletType.DmgEnemy) {
                    hp -= 50;
                    handler.removeObject(tempObject);
                }

            }
        }

        if (hp <= 0) {
            handler.addObject(new GoldCrate(x, y, ID.GoldCrate, ss, 1));
            handler.removeObject(this);
        }
    }

    /**
     * Metoda rysujaca przeciwnika.
     *
     * @param g Odnosnik do miejsca w ktorym mamy narysowac obiekt
     */
    public void render(Graphics g) {
        g.drawImage(enemy_image, x, y, null);
    }

    /**
     * Zwraca prostokat bedacy obrysem obiektu
     *
     * @return Obrys skrzyni
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    /**
     * Prywatna metoda zwracajaca prostokat wiekszy od obrysu obiektu.
     * Sluzy do plynniejszego zarzadania odbiciami od scian.
     *
     * @return Obrys 2 krotnie wiekszy niz rzeczywisty obiekt
     */
    private Rectangle getBounds2() {
        return new Rectangle(x - 16, y - 16, 64, 64);
    }
}
