package Model;

import java.awt.*;
import java.util.LinkedList;

/**
 * Klasa bedaca kontenerem obiektow gry.
 * Przechowuje stan naszej gry.
 * Wewnatrz jest zaimplementowana jako LinkedList
 */
public class Handler {
    /**
     * Lista przechowujaca obiekty gry
     */
    private LinkedList<GameObject> object = new LinkedList<GameObject>();

    /**
     * Metoda zwracajaca liste obiektow gry
     *
     * @return Lista obiektow gry
     */
    public LinkedList<GameObject> getObject() {
        return object;
    }

    /**
     * Zmienna mowiaca o kierunku poruszania sie bohatera, tu do gory
     */
    private boolean up = false;
    /**
     * Zmienna mowiaca o kierunku poruszania sie bohatera, tu do dolu
     */
    private boolean down = false;
    /**
     * Zmienna mowiaca o kierunku poruszania sie bohatera, tu w prawo
     */
    private boolean right = false;
    /**
     * Zmienna mowiaca o kierunku poruszania sie bohatera, tu w lewo
     */
    private boolean left = false;

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Metoda uaktualniajaca wszystkie obiekty bedace elementami listy
     */
    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);

            temp.tick();
        }
    }

    /**
     * Metoda rysujaca wszystkie obiekty bedace elementami listy
     *
     * @param g Odnosnik do miejsca w ktorym mamy narysowac obiekt
     */
    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);

            temp.render(g);
        }
    }

    /**
     * Dodanie obiektu do listy
     *
     * @param tempObject Nowy obiekt
     */
    public void addObject(GameObject tempObject) {
        object.add(tempObject);
    }

    /**
     * Usuniecie elementu z listy
     *
     * @param tempObject Usuwany obiekt
     */
    public void removeObject(GameObject tempObject) {
        object.remove(tempObject);
    }
}
