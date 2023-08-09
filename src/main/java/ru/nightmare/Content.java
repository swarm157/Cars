package ru.nightmare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Content extends JPanel {


    public static Image road;
    public static Image car1;
    public static Image car2;
    public static Image car3;
    public static Image car4;
    public static Image car5;
    public static Image car6;
    public static Image player;
    public static ArrayList<Image> imgs;

    {
        setDoubleBuffered(true);
        ImageIcon roadIcon = new ImageIcon("src\\main\\resources\\road.jpg");
        ImageIcon car1Icon = new ImageIcon("src\\main\\resources\\car1.jpeg");
        ImageIcon car2Icon = new ImageIcon("src\\main\\resources\\car2.jpg");
        ImageIcon car3Icon = new ImageIcon("src\\main\\resources\\car3.jpeg");
        ImageIcon car4Icon = new ImageIcon("src\\main\\resources\\car4.jpg");
        ImageIcon car5Icon = new ImageIcon("src\\main\\resources\\car5.jpeg");
        ImageIcon car6Icon = new ImageIcon("src\\main\\resources\\car6.jpg");
        //ImageIcon playerIcon = new ImageIcon("C:\\Users\\nightmare\\IdeaProjects\\Cars\\src\\main\\resources\\player.png");
        ImageIcon playerIcon = new ImageIcon("src\\main\\resources\\player.png");

        /*try {
            road = ImageIO.read(new File("C:\\Users\\nightmare\\IdeaProjects\\Cars\\src\\main\\resources\\car2.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        road = roadIcon.getImage();
        car1 = car1Icon.getImage();
        car2 = car2Icon.getImage();
        car3 = car3Icon.getImage();
        car4 = car4Icon.getImage();
        car5 = car5Icon.getImage();
        car6 = car6Icon.getImage();
        imgs = new ArrayList<>(){{
            add(car1);
            add(car2);
            add(car3);
            add(car4);
            add(car5);
            add(car6);
        }};
        player = playerIcon.getImage();




    }

    @Override
    public void paintComponent(Graphics gg) {
        //super.paint(g);
        Graphics2D g = (Graphics2D)gg;





        int a = 70;
        g.setColor(new Color(35, 25, 5));
        g.fillRect(0, 0, Boot.winW, Boot.winH);
        g.setColor(Color.black);
        for(int i =0; i < 3; i++) {
            g.drawImage(road, i*Boot.winW/3, 0, Boot.winW/3, Boot.winH, null);
        }
        for(Enemy e: Game.enemies) {
            g.drawImage(e.image, e.x, e.y, Enemy.xSize, Enemy.ySize, null);
            //System.out.println(e.toString());
            //System.out.println(Enemy.xSize);
            //System.out.println(Enemy.ySize);
        }
        if (Game.xWeaponActive) {
            g.setColor(new Color(255-Game.xWeaponRemain*3, Game.xWeaponRemain*3, Game.xWeaponRemain));
            g.drawRect(Game.pX+15, Game.pY+50, Enemy.xSize-30, Enemy.ySize-60);
        } else {
            g.drawImage(player, Game.pX, Game.pY, Enemy.xSize, Enemy.ySize, null);
        }
        g.setColor(new Color(90+a/2, 30+a, 5, 90));
        g.fillRoundRect(Boot.winW/100*88, -3, Boot.winW/100*90, Boot.winH/100*28, 9, 9);
        g.setColor(Color.yellow);
        g.drawRoundRect(Boot.winW/100*88, -3, Boot.winW/100*90, Boot.winH/100*28, 9, 9);
        g.drawString("Score: " + Game.score, Boot.winW/100*90, Boot.winH/100*5);
        g.drawString("Record: " + Game.record, Boot.winW/100*90, Boot.winH/100*10);
        g.drawString("Enemy speed: " + Game.enemySpeed, Boot.winW/100*90, Boot.winH/100*15);
        g.drawString("X Weapon: " + Game.xWeapon, Boot.winW/100*90, Boot.winH/100*20);
        g.drawString("Health: " + Game.health, Boot.winW/100*90, Boot.winH/100*25);
    }
}
