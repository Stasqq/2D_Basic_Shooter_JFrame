package Model;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject{

    private Handler handler;
    private Random r = new Random();
    private int choose = 0;
    private int hp = 100;

    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler=handler;
    }

    public void tick() {
        x += velX;
        y += velY;

        choose = r.nextInt(10);

        for(int i=0;i < handler.getObject().size(); i++){
            GameObject tempObject=handler.getObject().get(i);

            if(tempObject.getId() == ID.Block){
                if(getBounds2().intersects(tempObject.getBounds())){
                    x += (velX*3) * -1;
                    y += (velY*3) * -1;
                    velX *= -1;
                    velY *= -1;
                }else if(choose == 0){
                    velX=(r.nextInt(4- -4)+ -4);
                    velY=(r.nextInt(4- -4)+ -4);
                }
            }

            if(tempObject.getId() == ID.Bullet){
                if(getBounds().intersects(tempObject.getBounds())){
                    hp -= 50;
                    handler.removeObject(tempObject);
                }

            }
        }

        if(hp <= 0){
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,32,32);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    private Rectangle getBounds2() {
        return new Rectangle(x-16,y-16,64,64);
    }
}
