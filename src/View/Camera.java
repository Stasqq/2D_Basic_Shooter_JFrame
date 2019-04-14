package View;

import Model.*;


public class Camera {
    private float x,y;

    public Camera(float x, float y){
        this.x=x;
        this.y=y;
    }

    public void tick(Player object){
        x+=((object.getX()-x)-1600/2*0.5F);
        y+=((object.getX()-y)-900/2*0.5F);
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
