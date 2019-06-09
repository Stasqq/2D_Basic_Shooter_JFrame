package Model;

import View.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Reprezentacja wystrzelonego pocisku.
 */
public class Bullet extends GameObject {
    /**
     * Przechowuje obiekty gry
     */
    private Handler handler;
    /**
     * Zmienna enum do rozroznienia pociskow wystrzelonych przez gracza i przeciwnikow.
     * Potrzebne zeby pociski wystrzelone przez bossa nie zabijaly przeciwnikow.
     */
    private BulletType bt;
    /**
     * Grafika wyswietlana przed metode render.
     */
    private BufferedImage bullet_image;

    /**
     * Reprezentacja wystrzelonego pocisku.
     *
     * @param x       Wspolrzedna x polozenia z ktorego ma zostac wystrzelony pocisk
     * @param y       Wspolrzedna y polozenia z ktorego ma zostac wystrzelony pocisk
     * @param id      Rodzaj obiektu
     * @param handler Odnosnik do kontenera z obiektami gry
     * @param mx      Wspolrzedna x polozenia myszki, sluzaca potem do wyliczenia przyspieszenia pocisku
     * @param my      Wspolrzedna y polozenia myszki, sluzaca potem do wyliczenia przyspieszenia pocisku
     * @param ss      Odnoscik do obiektu przechowujacego plik graficzny, z ktorego potem rysujemy nasz obiekt
     * @param bt      Zmienna enum do rozroznienia pociskow wystrzelonych przez gracza i przeciwnikow
     */
    public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss, BulletType bt) {
        super(x, y, id, ss);
        this.handler = handler;

        this.bt = bt;

        velX = (mx - x) / (float) 10;
        velY = (my - y) / (float) 10;

        if (bt == BulletType.DmgEnemy)
            bullet_image = ss.grabImage(4, 2, 32, 32);
        else
            bullet_image = ss.grabImage(4, 1, 32, 32);

    }

    /**
     * Reprezentacja wystrzelonego pocisku.
     *
     * @param x       Wspolrzedna x polozenia z ktorego ma zostac wystrzelony pocisk
     * @param y       Wspolrzedna y polozenia z ktorego ma zostac wystrzelony pocisk
     * @param id      Rodzaj obiektu
     * @param handler Odnosnik do kontenera z obiektami gry
     * @param ss      Odnoscik do obiektu przechowujacego plik graficzny, z ktorego potem rysujemy nasz obiekt
     * @param bt      Zmienna enum do rozroznienia pociskow wystrzelonych przez gracza i przeciwnikow
     * @param vX      Wartosc przyspieszenia pocisku w osi x
     * @param vY      Wartosc przyspieszenia pocisku w osi y
     */
    public Bullet(int x, int y, ID id, Handler handler, SpriteSheet ss, BulletType bt, int vX, int vY) {
        super(x, y, id, ss);
        this.handler = handler;

        this.bt = bt;

        velX = vX;
        velY = vY;

        if (bt == BulletType.DmgEnemy)
            bullet_image = ss.grabImage(4, 2, 32, 32);
        else
            bullet_image = ss.grabImage(4, 1, 32, 32);
    }

    /**
     * Uaktualnienie stanu obiektu.
     * Tutaj: zmiana polozenia obiektu, zalezna od jego przyspieszenia oraz sprawdzenie kolizji z blokami.
     */
    public void tick() {
        x += velX;
        y += velY;

        for (int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject = handler.getObject().get(i);

            if (tempObject.getId() == ID.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }
    }

    /**
     * Metoda rysujaca pocisk.
     *
     * @param g Odnosnik do miejsca w ktorym mamy narysowac obiekt
     */
    public void render(Graphics g) {
        g.drawImage(bullet_image, x, y, null);
    }

    /**
     * Zwraca prostokat bedacy obrysem obiektu
     *
     * @return Obrys pocisku
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 9, 9);
    }

    /**
     * Metoda zwracajaca rodzaj pocisku
     *
     * @return Rodzaj pocisku.
     */
    public BulletType getBt() {
        return bt;
    }
}
