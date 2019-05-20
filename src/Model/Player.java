package Model;

import java.awt.*;

public class Player extends GameObject{

    private Handler handler;
    private int ammo;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler=handler;
        ammo=50;
    }

    public void tick() {
        x += velX;
        y += velY;

        collision();

        if(handler.isUp()) velY =-5;
        else if(!handler.isDown()) velY=0;

        if(handler.isDown()) velY=5;
        else if(!handler.isUp()) velY=0;

        if(handler.isRight()) velX=5;
        else if(!handler.isLeft()) velX=0;

        if(handler.isLeft()) velX=-5;
        else if(!handler.isRight()) velX=0;
    }

    private void collision(){
        for(int i=0;i<handler.getObject().size();i++){
            GameObject tempObject;
            try {
                tempObject = handler.getObject().get(i);
            }catch(IndexOutOfBoundsException e) {
                continue;
            }

            if(tempObject.getId() == ID.Block){
                if(getBounds().intersects(tempObject.getBounds())){
                    x += velX *-1;
                    y += velY *-1;
                }
            }

            if(tempObject.getId() == ID.Create){
                if(getBounds().intersects(tempObject.getBounds())){
                    ammo += 10;
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    public void decAmmo(){
        ammo -=1;
    }

    public int getAmmo(){
        return ammo;
    }

    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x,y,32,48);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,48);
    }
}
