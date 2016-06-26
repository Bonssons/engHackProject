/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enghackproject;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author shireen
 */
public class Stone {
    
    //private boolean alive;
    private int x, y, speed_x, speed_y, repeat;
    int left;

    private Image stone;
    private ImageIcon left_to_right = new ImageIcon(this.getClass().getResource("stone1.png"));
    private ImageIcon right_to_left = new ImageIcon(this.getClass().getResource("stone1.png"));
    
    static ArrayList<Bullet> bullet;
    

    public Stone(int x, int y){
        repeat = 0;
        this.x = x;
        this.y = y;
        
        speed_x = 1;
        speed_y = 0;
        bullet = new ArrayList();
    }
    
    public void draw(JPanel jpanel, Graphics2D g2d) {
        g2d.drawImage(stone, x,y, jpanel);    
        
    }
    
    public void move(){
        x += speed_x;
        y += speed_y;
        limits();
      
    }
    
    private void limits(){
        
        if(x == 0) {
            speed_x = 1;
        }
        else if(x == 350) {
            speed_x = -1;
        }

        if(y == 0) {
            speed_y = 1;
        }
        else if(y == 0) {
            speed_y = -1;
        }
        if(x > 0) {
            stone = left_to_right.getImage();
        }else {
            stone = right_to_left.getImage();
        }
    }
    
   
    public static ArrayList getBullets(){
        return bullet;
    }
    public void fire(){
        Bullet b = new Bullet((left+60),(y+154/2));
        bullet.add(b);
    }
  

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the speed_x
     */
    public int getSpeed_x() {
        return speed_x;
    }

    /**
     * @param speed_x the speed_x to set
     */
    public void setSpeed_x(int speed_x) {
        this.speed_x = speed_x;
    }

    /**
     * @return the speed_y
     */
    public int getSpeed_y() {
        return speed_y;
    }

    /**
     * @param speed_y the speed_y to set
     */
    public void setSpeed_y(int speed_y) {
        this.speed_y = speed_y;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return stone;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.stone = image;
    }
    
    
    }

