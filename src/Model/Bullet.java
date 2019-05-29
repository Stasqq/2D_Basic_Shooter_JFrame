package Model;

import View.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{

    private Handler handler;
    private BulletType bt;
    private BufferedImage bullet_image;

    public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss,BulletType bt) {
        super(x, y, id,ss);
        this.handler=handler;

        this.bt=bt;

        velX = (mx-x)/(float)10;
        velY = (my-y)/(float)10;

        if(bt == BulletType.DmgEnemy)
            bullet_image=ss.grabImage(4,2,32,32);
        else
            bullet_image=ss.grabImage(4,1,32,32);

    }

    public Bullet(int x, int y, ID id, Handler handler, SpriteSheet ss,BulletType bt, int vX, int vY) {
        super(x, y, id,ss);
        this.handler=handler;

        this.bt=bt;

        velX = vX;
        velY = vY;

        if(bt == BulletType.DmgEnemy)
            bullet_image=ss.grabImage(4,2,32,32);
        else
            bullet_image=ss.grabImage(4,1,32,32);
    }



    public void tick() {
        x+=velX;
        y+=velY;

        for(int i=0;i<handler.getObject().size();i++){
            GameObject tempObject=handler.getObject().get(i);

            if(tempObject.getId() == ID.Block){
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(this);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.drawImage(bullet_image,x,y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,9,9);
    }

    public BulletType getBt(){
        return bt;
    }
}
