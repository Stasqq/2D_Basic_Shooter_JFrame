package Model;

import View.*;

import java.io.IOException;

/**
 * Część projektu odpowiedzialna za przedstawienie modelu programu
 */
public class Model {
    /**
     * Przechowuje obiekty gry
     */
    private Handler handler;
    /**
     * Kamera obsluguje ktora czesc mapy widzi gracz
     */
    private Camera camera;
    /**
     * Reprezentacja bohatera sterowanego przez gracza
     */
    private Player player;

    /**
     * Część projektu odpowiedzialna za przedstawienie modelu programu
     */
    public Model() {
        camera = new Camera(0, 0);
        handler = new Handler();
    }

    /**
     * Metoda zwracajaca kontener z lista obiektow
     *
     * @return Kontener z lista obiektow gry
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * Metoda uaktualniajaca stan calej gry, mianowicie uaktualnia polozenie kamery oraz wywoluje metode handlera, ktora uaktualnia wszystkie obiekty gry.
     */
    public void tick() {
        camera.tick(player);

        handler.tick();
    }

    /**
     * Metoda zwracajaca wspolrzedna x polozenia kamery
     *
     * @return Wspolrzedna x polozenia kamery
     */
    public float getCameraX() {
        return camera.getX();
    }

    /**
     * Metoda zwracajaca wspolrzedna y polozenia kamery
     *
     * @return Wspolrzedna y polozenia kamery
     */
    public float getCameraY() {
        return camera.getY();
    }

    /**
     * Metoda zwracajaca odnosnik do kamery
     *
     * @return Odnosnik do kamery
     */
    public Camera getCamera() {
        return camera;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Metoda zwracajaca ilosc punktow zycia gracza
     *
     * @return Punkty zycia gracza
     */
    public int getPlayerHp() {
        return player.getHP();
    }

    /**
     * Metoda zwracajaca ilosc amunicji gracza
     *
     * @return Ilosc amunicji gracza
     */
    public int getPlayerAmmo() {
        return player.getAmmo();
    }

    /**
     * Metoda zwracajaca ilosc punktow/zlota gracza
     *
     * @return Ilosc punktow gracza
     */
    public int getPlayerGold() {
        return player.getGold();
    }
}
