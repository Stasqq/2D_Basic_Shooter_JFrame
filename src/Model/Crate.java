package Model;

import View.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Crate extends GameObject{

    protected BufferedImage crateImage;

    public Crate(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id,ss);

        crateImage=ss.grabImage(3,1,32,32);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(crateImage,x,y,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
