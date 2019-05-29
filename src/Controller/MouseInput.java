package Controller;

import Model.*;
import View.Camera;
import View.SpriteSheet;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private Camera camera;
    private SpriteSheet ss;

    public MouseInput(Handler handler,Camera camera,SpriteSheet ss){
        this.handler=handler;
        this.camera=camera;
        this.ss=ss;
    }

    public void mousePressed(MouseEvent e){
        int mx=(int)(e.getX()+camera.getX());
        int my=(int)(e.getY()+camera.getY());

        for(int i=0;i<handler.getObject().size();i++){
            GameObject tempObject= handler.getObject().get(i);

            if(tempObject.getId() == ID.Player){
                Player player = (Player) handler.getObject().get(i);
                if((int)((mx-tempObject.getX()-16)/(float)10)!=0 && (int)((my-tempObject.getY()-24)/(float)10) !=0 && player.getAmmo() >= 1){
                    player.decAmmo();
                    handler.addObject(new Bullet(tempObject.getX()+8,tempObject.getY()+12,ID.Bullet,handler,mx,my,ss,BulletType.DmgEnemy));
                }
            }
        }
    }
}
