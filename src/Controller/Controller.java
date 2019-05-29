package Controller;
import View.*;
import Model.*;

import java.awt.*;
import java.io.File;
import java.util.concurrent.*;

/**
 * Część odpowiedzialna za kontrolę nad grą, kontaktuje się z modelem i widokiem
 */
public class Controller extends Canvas implements Runnable {
    private boolean isRunning=false;
    private Thread thread;
    private View view;
    private Model model;

    private ThreadPoolExecutor executor;

    public Controller(View v, Model m) {
        view=v;
        model=m;
        view.addController(this);
        view.loadMap();

        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        Music player = new Music("2.wav");
        executor.execute(player);
        executor.execute(this);

        //start();
        this.addMouseListener(new MouseInput(model.getHandler(),model.getCamera(),view.getSpriteSheet()));
        this.addKeyListener(new KeyInput(model.getHandler()));
    }
/*
    private void start() {
        isRunning=true;
        thread=new Thread(this);
        thread.start();
    }
*/
    private void stop() {
        isRunning=false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void run() {
        isRunning=true;
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta=0;
        long timer=System.currentTimeMillis();
        int frames=0;
        while(isRunning){
            long now=System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            while(delta >= 1){
                model.tick();
                //updates++;
                delta--;
            }
            view.render(this);
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer+=1000;
                frames=0;
                //updates=0;
            }
        }
        stop();
    }
}
