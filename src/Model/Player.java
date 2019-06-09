package Model;

import View.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Reprezentacja bohatera, ktorym steruje gracz
 */
public class Player extends GameObject {
    /**
     * Przechowuje obiekty gry
     */
    private Handler handler;
    /**
     * Ilosc dostepnej amunicji
     */
    private int ammo;
    /**
     * Grafika bohatera
     */
    private BufferedImage player_image;
    /**
     * Ilosc punktow zycia gracza
     */
    private int hp = 200;
    /**
     * Ilosc punkotw gracza
     */
    private int gold = 0;

    /**
     * Reprezentacja bohatera, ktorym steruje gracz
     *
     * @param x       Wspolrzedna x polozenia
     * @param y       Wspolrzedna y polozenia
     * @param id      Rodzaj obiektu
     * @param handler Odnosnik do kontenera z obiektami gry
     * @param ss      Odnoscik do obiektu przechowujacego plik graficzny, z ktorego potem rysujemy nasz obiekt
     */
    public Player(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        ammo = 50;
        player_image = ss.grabImage(1, 1, 32, 48);
    }

    /**
     * Uaktualnienie stanu obiektu.
     * Tutaj: zmiana polozenia obiektu, sprawdzenie kolizji z blokami, przeciwnikami, pociskami.
     */
    public void tick() {
        x += velX;
        y += velY;

        collision();

        if (handler.isUp()) velY = -5;
        else if (!handler.isDown()) velY = 0;

        if (handler.isDown()) velY = 5;
        else if (!handler.isUp()) velY = 0;

        if (handler.isRight()) velX = 5;
        else if (!handler.isLeft()) velX = 0;

        if (handler.isLeft()) velX = -5;
        else if (!handler.isRight()) velX = 0;

        if (hp <= 0) {
            handler.removeObject(this);
        }
    }

    /**
     * Metoda sprawdzajaca i obslugujaca kolizje z roznymi typami obiektow.
     */
    private void collision() {
        for (int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject;
            try {
                tempObject = handler.getObject().get(i);
            } catch (IndexOutOfBoundsException e) {
                continue;
            }

            if (tempObject.getId() == ID.Bullet) {
                Bullet bullet = (Bullet) tempObject;
                if (getBounds().intersects(tempObject.getBounds()) && bullet.getBt() == BulletType.DmgPlayer) {
                    hp -= 40;
                    handler.removeObject(tempObject);
                }
            }

            if (tempObject.getId() == ID.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hp -= 2;
                }
            }


            if (tempObject.getId() == ID.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }

            if (tempObject.getId() == ID.Create) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    ammo += 10;
                    handler.removeObject(tempObject);
                }
            }

            if (tempObject.getId() == ID.GoldCrate) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    GoldCrate temp = (GoldCrate) tempObject;
                    gold += temp.getValue();
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    /**
     * Dekrementacja ilosc amunicji gracza
     */
    public void decAmmo() {
        ammo -= 1;
    }

    /**
     * Metoda zwracajaca ilosc amunicji
     *
     * @return Ilosc amunicji
     */
    public int getAmmo() {
        return ammo;
    }

    /**
     * Metoda rysujaca bohatera.
     *
     * @param g Odnosnik do miejsca w ktorym mamy narysowac obiekt
     */
    public void render(Graphics g) {
        g.drawImage(player_image, x, y, null);
    }

    /**
     * Zwraca prostokat bedacy obrysem obiektu
     *
     * @return Obrys bohatera
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 48);
    }

    /**
     * Metoda zwracajaca ilosc punktow zycia gracza
     *
     * @return Punkty zycia
     */
    public int getHP() {
        return hp;
    }

    /**
     * Metoda zwracajaca ilosc punktow gracza
     *
     * @return Punkty
     */
    public int getGold() {
        return gold;
    }
}
