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
import java.awt.event.KeyEvent;
/**
 *
 * @author Rafael
 */
public class Gun {
    private int general_speed = 3;
    private int x, y, speed_x, speed_y;
    private Image image;
    private ImageIcon icon_still = new ImageIcon(this.getClass().getResource("slingshot.png"));
    private ImageIcon icon_fire = new ImageIcon(this.getClass().getResource("slingshot_fire.png"));
    
    public Gun(int x, int y){
        this.x = x;
        this.y = y;
        speed_x = 0;
        speed_y = 0;
        image = icon_still.getImage();
        //image.flush();
    }
    
    public void stop_firing() {
        image = icon_still.getImage();
    }
    
    public void fire() {
        image = icon_fire.getImage();
    }
    
    
    public void draw(JPanel jpanel, Graphics2D g2d) {
        g2d.drawImage(image, x,y, jpanel);      
    }
    
    public void move(){
        x += speed_x;
        limits();
    }
    
    public void updateDirection(int direction) {
        if (direction == KeyEvent.VK_LEFT) {
            speed_x = -general_speed;
        }else if (direction == KeyEvent.VK_RIGHT) {
            speed_x = general_speed;
        }else speed_x = 0;
    }
    
    private void limits(){
        if(x <= 0) {
            x = 1;
        }
        else if(x >= 451) {
            x = 450;
        }
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
