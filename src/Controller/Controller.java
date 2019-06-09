package Controller;

import View.*;
import Model.*;

import java.awt.*;
import java.util.concurrent.*;

/**
 * Czesc odpowiedzialna za kontrole nad gra, kontaktuje sie z modelem i widokiem.
 * Tutejsza metoda run() zawiera glowna petle gry.
 */
public class Controller extends Canvas implements Runnable {
    /**
     * Odnosnik do obiektu typu View. Wyswietlanie gry bedzie sie odbywac przez polecenia pochadace z Controllera do View.
     */
    private View view;
    /**
     * Odnosnik do obiektu typu Model. W petli bedza uaktualniane dane znajdujace sie w modelu.
     */
    private Model model;

    /**
     * Czesc odpowiedzialna za kontrole nad gra, kontaktuje sie z modelem i widokiem.
     * Laczy dwa watki, tj: gre i odtwarzana muzyke w tle.
     * Tutejsza metoda run() zawiera glowna petle gry.
     *
     * @param v Odnosnik do obiektu typu View
     * @param m Odnosnik do obiektu typu Model
     */
    public Controller(View v, Model m) {
        view = v;
        model = m;
        view.addController(this);
        view.loadMap();
        ThreadPoolExecutor executor;

        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        executor.execute(this);
        Music player = new Music("background_w.wav");
        executor.execute(player);

        //start();
        this.addMouseListener(new MouseInput(model.getHandler(), model.getCamera(), view.getSpriteSheet()));
        this.addKeyListener(new KeyInput(model.getHandler()));
    }

    /**
     * Metoda bedaca glowna petla gry.
     * Petla ktora zostala tu wykorzystana ustawia 60 klatek na sekunde
     * Nadpisana metoda dla watku.
     */
    public void run() {
        boolean isRunning = true;
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                model.tick();
                //updates++;
                delta--;
            }
            view.render(this);
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
                //updates=0;
            }
        }
    }
}
