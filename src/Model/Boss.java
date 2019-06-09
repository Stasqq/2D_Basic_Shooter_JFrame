package Model;

import View.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Reprezentacja nieruchomego przeciwnika, ktory strzela pociskami.
 */
public class Boss extends Enemy {
    /**
     * Licznik dzieki ktoremu mozemy kontrolowac czestotliwosc wystrzalow pociskow
     */
    private int counter;
    /**
     * Przechowuje obiekty gry
     */
    private Handler handler;
    /**
     * Ilosc punktow zycia
     */
    private int hp;
    /**
     * Grafika wyswietlana przed metode render
     */
    private BufferedImage boss_image;
    /**
     * Grafika nieruchomego bossa
     */
    private BufferedImage boss;
    /**
     * Grafika strzelajacego bossa
     */
    private BufferedImage boss_shots;

    /**
     * Reprezentacja nieruchomego przeciwnika, ktory strzela pociskami
     *
     * @param x       Wspolrzedna x przeciwnika
     * @param y       Wspolrzedna y przeciwnika
     * @param id      Rodzaj obiektu
     * @param handler Odnosnik do kontenera z obiektami gry
     * @param ss      Odnoscik do obiektu przechowujacego plik graficzny, z ktorego potem rysujemy nasz obiekt
     */
    public Boss(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, handler, ss);
        this.handler = handler;

        hp = 300;

        boss = ss.grabImage(5, 1, 64, 64);
        boss_shots = ss.grabImage(7, 1, 64, 64);

        boss_image = boss;
    }

    /**
     * Uaktualnienie stanu obiektu.
     * Tutaj: uaktualnienie wartosci counter, wystrzal pociskow, sprawdzenie kolizji z pociskami bohatera, zmiana grafiki.
     */
    public void tick() {
        GameObject tempObject;
        for (int i = 0; i < handler.getObject().size(); i++) {
            try {
                tempObject = handler.getObject().get(i);
            } catch (Exception e) {
                continue;
            }

            if (tempObject.getId() == ID.Bullet) {
                Bullet bullet = (Bullet) tempObject;
                if (getBounds().intersects(tempObject.getBounds()) && bullet.getBt() == BulletType.DmgEnemy) {
                    hp -= 50;
                    handler.removeObject(tempObject);
                }
            }
        }

        if (counter == 100) {

            handler.addObject(new Bullet(x + 16, y + 16, ID.Bullet, handler, ss, BulletType.DmgPlayer, 7, 7));
            handler.addObject(new Bullet(x + 16, y + 16, ID.Bullet, handler, ss, BulletType.DmgPlayer, -7, -7));
            handler.addObject(new Bullet(x + 16, y + 16, ID.Bullet, handler, ss, BulletType.DmgPlayer, -7, 7));
            handler.addObject(new Bullet(x + 16, y + 16, ID.Bullet, handler, ss, BulletType.DmgPlayer, 7, -7));
            handler.addObject(new Bullet(x + 16, y + 16, ID.Bullet, handler, ss, BulletType.DmgPlayer, 10, 0));
            handler.addObject(new Bullet(x + 16, y + 16, ID.Bullet, handler, ss, BulletType.DmgPlayer, 0, 10));
            handler.addObject(new Bullet(x + 16, y + 16, ID.Bullet, handler, ss, BulletType.DmgPlayer, -10, 0));
            handler.addObject(new Bullet(x + 16, y + 16, ID.Bullet, handler, ss, BulletType.DmgPlayer, 0, -10));


            counter = 0;
            boss_image = boss_shots;
        } else {
            counter++;
            if (counter > 20)
                boss_image = boss;
        }

        if (hp <= 0) {
            handler.addObject(new GoldCrate(x + 16, y + 16, ID.GoldCrate, ss, 2));
            handler.removeObject(this);
        }
    }

    /**
     * Metoda rysujaca przeciwnika.
     *
     * @param g Odnosnik do miejsca w ktorym mamy narysowac obiekt
     */
    public void render(Graphics g) {
        g.drawImage(boss_image, x, y, null);
    }

    /**
     * Zwraca prostokat bedacy obrysem obiektu
     *
     * @return Obrys przeciwnika
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }
}
