package View;

import Model.*;

/**
 * Kamera, ktorej zadaniem jest poruszanie sie wraz z graczem, tak aby gracz widzial pozadana aktualnie czesc mapy.
 */
public class Camera {
    /**
     * Wspolzedna x polozenia
     */
    private float x;
    /**
     * Wspolzedna y polozenia
     */
    private float y;

    /**
     * Kamera, ktorej zadaniem jest poruszanie sie wraz z graczem, tak aby gracz widzial pozadana aktualnie czesc mapy.
     *
     * @param x Wspolzedna x polozenia
     * @param y Wspolzedna y polozenia
     */
    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Uaktualnienie polozenia kamery, jesli jest ono potrzebne
     *
     * @param object Odnosnik do obiektu reprezentujacego bohatera
     */
    public void tick(GameObject object) {
        x += ((object.getX() - x) - 1024 / 2) * 0.5f;
        y += ((object.getY() - y) - 600 / 2) * 0.5f;

        if (x <= 0) x = 0;
        if (x >= 1040) x = 1040;
        if (y <= 0) y = 0;
        if (y >= 590) y = 590;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
