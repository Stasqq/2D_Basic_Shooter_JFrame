package Controller;

import Model.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Klasa obslugujaca wejscie z klawiatury. Sluzy do poruszania sie bohatera
 */
public class KeyInput extends KeyAdapter {
    /**
     * Przechowuje obiekty gry
     */
    private Handler handler;

    /**
     * Klasa obslugujaca wejscie z klawiatury. Sluzy do poruszania sie bohatera
     *
     * @param handler Kontener przechowujacy obiekty gry
     */
    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    /**
     * Zmiana kierunku poruszania sie bohatera
     *
     * @param e Zdarzenie przycisniecia przycisku
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject = handler.getObject().get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W)
                    handler.setUp(true);
                if (key == KeyEvent.VK_S)
                    handler.setDown(true);
                if (key == KeyEvent.VK_A)
                    handler.setLeft(true);
                if (key == KeyEvent.VK_D)
                    handler.setRight(true);
            }
        }
    }

    /**
     * Zatrzymanie poruszania sie bohatera
     *
     * @param e Zdarzenie puszczenia przycisku
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.getObject().size(); i++) {
            GameObject tempObject = handler.getObject().get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W)
                    handler.setUp(false);
                if (key == KeyEvent.VK_S)
                    handler.setDown(false);
                if (key == KeyEvent.VK_A)
                    handler.setLeft(false);
                if (key == KeyEvent.VK_D)
                    handler.setRight(false);
            }
        }
    }
}
