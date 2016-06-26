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

/**
 *
 * @author Rafael
 */

public class Bird {
    private static int general_speed = 1;
    private boolean alive, goingRight;
    private int x, y, speed_x, speed_y, repeat;
    private Image image;
    private ImageIcon lr = new ImageIcon(this.getClass().getResource("bird_flapping_lr.gif"));
    private ImageIcon rl = new ImageIcon(this.getClass().getResource("bird_flapping_rl.gif"));
    private ImageIcon dlr = new ImageIcon(this.getClass().getResource("bird_dying_lr.gif"));
    private ImageIcon drl = new ImageIcon(this.getClass().getResource("bird_dying_rl.gif"));
    
    public Bird(int x, int y){
        alive = true;
        goingRight = true;
        repeat = 0;
        this.x = x;
        this.y = y;
        speed_x = general_speed;
        speed_y = 0;
    }
    
    public void die() {
        alive = false;
        //speed_x = 0;
        speed_y = 5;
        general_speed++;
    }
    
    
    public void draw(JPanel jpanel, Graphics2D g2d) {
        g2d.drawImage(image, x,y, jpanel);      
    }
    
    public void move(){
        x += speed_x;
        y += speed_y;
        resetAnimation();
        changeImage();
        limits();
    }
     
   private void resetAnimation() {
       if(isAlive()) {
            if (repeat++ == 48) {
                repeat = 0;
                image.flush();
            }
        }
    }
    
    private void changeImage() {
        if(speed_x > 0) {
            if (isAlive()) {
                image = lr.getImage();
                goingRight = true;
            }else {
                image = dlr.getImage();
                speed_x = 0;
            }
        }else if (speed_x < 0){
            if (isAlive()) {
                image = rl.getImage();
                goingRight = false;
            }else {
                image = drl.getImage();       
                speed_x = 0;
            }
        }
    }
    
    private void limits(){
        if(x <= 0) {
            speed_x = general_speed;
        }
        else if(x >= 650) {
            speed_x = -general_speed;
        }

//      if(y == 0) {
//          speed_y = 1;
//      }
//      else if(y == 550) {
//          speed_y = -1;
//      }        
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

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }
    
    public static void resetBirdSpeed() {
        general_speed = 1;
    }
    
    public static int getGeneralSpeed(){
        return general_speed;
    }
}
