package Model;

import View.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Boss extends Enemy {

    private int counter;
    private Handler handler;
    private int hp;
    private BufferedImage boss_image;
    private BufferedImage boss;
    private BufferedImage boss_shots;
    private int angle=0;

    public Boss(int x, int y, ID id, Handler handler, SpriteSheet ss){
        super(x, y, id,handler,ss);
        this.handler=handler;

        hp=300;

        boss = ss.grabImage(5,1,64,64);
        boss_shots = ss.grabImage(7,1,64,64);

        boss_image = boss;
    }

    public void tick(){
        GameObject tempObject;
        for(int i=0;i < handler.getObject().size(); i++){
            try {
                tempObject = handler.getObject().get(i);
            }catch(Exception e){
                continue;
            }

            if(tempObject.getId() == ID.Bullet){
                Bullet bullet=(Bullet)tempObject;
                if(getBounds().intersects(tempObject.getBounds()) && bullet.getBt() == BulletType.DmgEnemy){
                    hp -= 50;
                    handler.removeObject(tempObject);
                }
            }
        }

        if(counter == 100){

            handler.addObject(new Bullet(x+16,y+16,ID.Bullet,handler,ss,BulletType.DmgPlayer,7,7));
            handler.addObject(new Bullet(x+16,y+16,ID.Bullet,handler,ss,BulletType.DmgPlayer,-7,-7));
            handler.addObject(new Bullet(x+16,y+16,ID.Bullet,handler,ss,BulletType.DmgPlayer,-7,7));
            handler.addObject(new Bullet(x+16,y+16,ID.Bullet,handler,ss,BulletType.DmgPlayer,7,-7));
            handler.addObject(new Bullet(x+16,y+16,ID.Bullet,handler,ss,BulletType.DmgPlayer,10,0));
            handler.addObject(new Bullet(x+16,y+16,ID.Bullet,handler,ss,BulletType.DmgPlayer,0,10));
            handler.addObject(new Bullet(x+16,y+16,ID.Bullet,handler,ss,BulletType.DmgPlayer,-10,0));
            handler.addObject(new Bullet(x+16,y+16,ID.Bullet,handler,ss,BulletType.DmgPlayer,0,-10));


            counter=0;
            boss_image=boss_shots;
        }else{
            counter++;
            if(counter>20)
            boss_image=boss;
        }

        if(hp <= 0){
            handler.addObject(new GoldCrate(x+16,y+16,ID.GoldCrate,ss,2));
            handler.removeObject(this);
        }
    }


    public void render(Graphics g) {
        g.drawImage(boss_image,x,y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,64,64);
    }
}
