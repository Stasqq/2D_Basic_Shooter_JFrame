package Model;

/**
 * Część projektu odpowiedzialna za przedstawienie modelu programu
 */
public class Model {
    private Handler handler;
    public Model() {
        handler=new Handler();
    }

    public Handler getHandler() {
        return handler;
    }

    public void tick(){
        handler.tick();
    }

}
