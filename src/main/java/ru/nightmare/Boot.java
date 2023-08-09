package ru.nightmare;

import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;

public class Boot {
    static JFrame window;
    static Toolkit toolkit = Toolkit.getDefaultToolkit();
    static final int height = toolkit.getScreenSize().height;
    static final int width = toolkit.getScreenSize().width;
    static int winHP = 65, winWP = 45;
    static int winH = height /100*winHP,
               winW = width/100*winWP;
    static int winHL = (toolkit.getScreenSize().height-winH)/2,
               winWL = (toolkit.getScreenSize().width-winW) /2;
    public static void main(String[] args) {
        window = new JFrame("Yars") {{
            setSize(winW, winH);
            setLocation(winWL, winHL);
            setResizable(false);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            add(new Content());
            addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {

                    switch (e.getKeyChar()) {
                        case 'a':
                            Game.left = true;
                            break;
                        case 'd':
                            Game.right = true;
                            break;
                        case 's':
                            Game.down = true;
                            break;
                        case 'w':
                            Game.up = true;
                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    switch (e.getKeyChar()) {
                        case 'a':
                            Game.left = false;
                            break;
                        case 'd':
                            Game.right = false;
                            break;
                        case 's':
                            Game.down = false;
                            break;
                        case 'w':
                            Game.up = false;
                            break;
                        case ' ':
                            if (Game.xWeapon>0) {
                                Game.xWeaponActive = true;
                                Game.xWeaponRemain = 80;
                                Game.xWeapon--;
                            }
                            break;
                    }
                }
            });
            setVisible(true);
        }};
        Timer painter = new Timer(1000/50, e -> window.repaint()){{
        }};
        painter.start();
        Timer game = new Timer(1000/25, new Game()){{
        }};
        game.start();
        new Thread(() -> {
            while (true) {
                try {
                    //String mp3File = "src/resources/bgr.mp3";
                    String mp3File = "C:\\Users\\nightmare\\IdeaProjects\\Cars\\src\\main\\resources\\bgr.mp3";
                    Player player = new Player(new FileInputStream(mp3File)) {{
                        play();
                    }};
                } catch (Exception e) {
                    System.out.println("Error playing MP3 file: " + e.getMessage());
                }
            }
        }).start();
    }

}
