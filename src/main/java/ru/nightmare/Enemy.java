package ru.nightmare;

import java.awt.*;

public class Enemy {

    public static int xSize = Boot.winW/5-10;
    public static int ySize = Boot.winH/3-10;
    public int x;
    public int y;
    public Image image;
    public Enemy(Image image) {
        this.x = (Boot.winW/3)*((int)(Math.random()*3))+((Boot.winW)/15);
        this.y -= Boot.winH/5-10;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "x=" + x +
                ", y=" + y +
                ", image=" + image +
                '}';
    }
}
