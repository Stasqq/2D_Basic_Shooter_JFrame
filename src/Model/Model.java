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
        handler=new Handler();
    }

    public Handler getHandler() {
        return handler;
    }

    public void tick(){
        for(int i=0;i<handler.getObject().size();i++){
            if(handler.getObject().get(i).getId() == ID.Player){
                Player pl=(Player)handler.getObject().get(i);
                camera.tick(pl);
            }
        }

       handler.tick();
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
