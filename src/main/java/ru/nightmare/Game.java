package ru.nightmare;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game implements ActionListener {

    public static boolean left = false;
    public static boolean right = false;
    public static boolean up = false;
    public static boolean down = false;

    public static int score = 0;
    public static int record = 0;

    public static int speed = 7;

    public static int health = 3;

    public static int xWeapon = 1;
    public static boolean xWeaponActive = false;
    public static int xWeaponRemain = 50;

    public static final int BEGINNING_ENEMY_SPEED = 5;
    public static int enemySpeed = BEGINNING_ENEMY_SPEED;

    public static int pX = 0;
    public static int pY = 0;

    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public static boolean checkCollision(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        Rectangle square1 = new Rectangle(x1, y1, width1, height1);
        Rectangle square2 = new Rectangle(x2, y2, width2, height2);

        return square1.intersects(square2);
    }

    public static void reset() {
        health=3;
        enemies = new ArrayList<>();
        score = 0;
        xWeapon=1;
        enemySpeed = BEGINNING_ENEMY_SPEED;
        pX = (Boot.winW/3-10)*2;
        pY = Boot.winH/2-10;
    }

    public static void eraseEnemy() {
        ArrayList<Enemy> del = new ArrayList<>();
        for(Enemy e: Game.enemies) {
            if (e.y>Boot.winH) {
                del.add(e);
            }
        }
        score+=del.size();
        if (score>record) record = score;
        if(enemySpeed!=BEGINNING_ENEMY_SPEED+(score/10)) xWeapon+=1;
        enemySpeed=BEGINNING_ENEMY_SPEED+(score/10);
        enemies.removeAll(del);
    }

    public static void generateEnemy() {
        if (g>50-enemySpeed) {
            if ((int)(Math.random()*3)!=2)
                enemies.add(new Enemy(Content.imgs.get((int)(Math.random()*Content.imgs.size()-1))));
            g=0;
        } else g++;
    }

    public static boolean checkPlayerCollision() {
        ArrayList<Enemy> del = new ArrayList<>();
        if (!xWeaponActive)
            for(Enemy e: Game.enemies) {
                if (checkCollision(Game.pX+15, Game.pY+50, Enemy.xSize-30, Enemy.ySize-60, e.x, e.y, Enemy.xSize, Enemy.ySize)) {
                    if (health>0) {
                        del.add(e);
                        health--;
                    } else
                        return true;
                }
            }
        enemies.removeAll(del);
        return false;
    }

    public static void movePlayer() {
        if (left) pX-=speed;
        if (right) pX+=speed;
        if (up) pY-=speed;
        if (down) pY+=speed;
        if (pX<0) pX=0;
        if (pX>Boot.winW-Enemy.xSize) pX=Boot.winW-Enemy.xSize;
        if (pY<0) pY=0;
        if (pY>Boot.winH-Enemy.ySize) pY=Boot.winH-Enemy.ySize;
    }

    static int g = 0;
    public static void moveEnemies() {

            for (Enemy e : Game.enemies) {
                e.y += enemySpeed;
            }

    }

    {
        reset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkPlayerCollision()&&!xWeaponActive) {
            reset();
        } else {
            generateEnemy();
            movePlayer();
            moveEnemies();
            eraseEnemy();
        }
        if (xWeaponActive) {
            xWeaponRemain--;
            if (xWeaponRemain<0)
                xWeaponActive=false;
        }
    }
}
