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
public class Wall {
    
    //private boolean alive;
    private int x, y, speed_x, speed_y;

    private Image image;
    private ImageIcon icon = new ImageIcon(this.getClass().getResource("wall.png"));
    
    static ArrayList<Bullet> bullet;
    

    public Wall(int x, int y){
        this.x = x;
        this.y = y;
        
        speed_x = 1;
        speed_y = 0;
    }
    
    public void draw(JPanel jpanel, Graphics2D g2d) {
        g2d.drawImage(image, x,y, jpanel);    
        
    }
    
    public void move(int movement){
        x += speed_x*movement;
        
        limits();
      
    }
    
    private void limits(){
        
        if(x <= 0 || x >= 650) {
            speed_x = -speed_x;
        }
        
        image = icon.getImage();
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
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
    
    
    }

