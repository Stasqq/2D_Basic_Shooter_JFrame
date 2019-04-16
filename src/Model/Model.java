package Model;

import View.*;

import java.io.IOException;

/**
 * Część projektu odpowiedzialna za przedstawienie modelu programu
 */
public class Model {
    private Handler handler;
    private Camera camera;
    public Model() {
        camera=new Camera(0,0);
        handler=new Handler();
    }

    public Handler getHandler() {
        return handler;
    }

    public void tick(){
        for(int i=0;i<handler.getObject().size();i++){
            if(handler.getObject().get(i).getId() == ID.Player){
                camera.tick(handler.getObject().get(i));
            }
        }

       handler.tick();
    }

    public float getCameraX(){
        return camera.getX();
    }

    public float getCameraY(){
        return camera.getY();
    }

    public Camera getCamera() {return camera;}
}
