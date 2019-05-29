package Model;

import View.SpriteSheet;

public class GoldCrate extends Crate {

    private int value;

    public GoldCrate(int x, int y, ID id, SpriteSheet ss,int gold_type) {
        super(x, y, id, ss);

        value = gold_type*gold_type*10;

        crateImage = ss.grabImage(gold_type,3,32,32);
    }

    public int getValue(){ return value; }
}
