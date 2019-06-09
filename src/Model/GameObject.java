package Model;

import View.SpriteSheet;

import java.awt.*;

/**
 * Abstrakcyjna klasa posiadajaca podstawowe informacje o obiekcie w grze.
 * Wiekszosc elementow z gry dziedziczy z tej klasy.
 */
public abstract class GameObject {
    /**
     * Wspolzedna polozenia x
     */
    protected int x;
    /**
     * Wspolzedna polozenia y
     */
    protected int y;
    /**
     * Przyspieszenie wzgeldem osi x
     */
    protected float velX = 0;
    /**
     * Przyspieszenie wzgeldem osi y
     */
    protected float velY = 0;
    /**
     * Rodzaj obiektu
     */
    protected ID id;
    /**
     * Odnoscik do obiektu przechowujacego plik graficzny, z ktorego potem rysujemy nasz obiekt
     */
    protected SpriteSheet ss;


    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }


    /**
     * Abstrakcyjna klasa posiadajaca podstawowe informacje o obiekcie w grze.
     * Wiekszosc elementow z gry dziedziczy z tej klasy.
     *
     * @param x           Skladowa polozenia x
     * @param y           Skladowa polozenia y
     * @param id          Rodzaj obiektu, oznaczany zmienna typu enum
     * @param spriteSheet Odnosnik do obiektu typu SpriteSheet, z ktorego nastepnie bedziemy wycinac i rysowac grafike danego obiektu(o ile jest on obiektem wymagajacym rysowania)
     */
    public GameObject(int x, int y, ID id, SpriteSheet spriteSheet) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.ss = spriteSheet;
    }

    /**
     * Metoda odswiezajaca stan obiektu.
     */
    public abstract void tick();

    /**
     * Metoda rysujaca obiekt.
     *
     * @param g Odnosnik do miejsca w ktorym mamy narysowac obiekt
     */
    public abstract void render(Graphics g);

    /**
     * Zwraca prostokat bedacy obrysem obiektu
     *
     * @return Prostokat bedacy obrysem obiektu, uwzgledniajacy polozenie i wymiary obiektu
     */
    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
