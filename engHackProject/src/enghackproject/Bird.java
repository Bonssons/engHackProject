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
    private int x, y, speed_x, speed_y, repeat;
    private Image image;
    private ImageIcon bird_lr = new ImageIcon(this.getClass().getResource("bird_flapping_lr.gif"));
    private ImageIcon bird_rl = new ImageIcon(this.getClass().getResource("bird_flapping_rl.gif"));
    
    public Bird(){
    repeat = 0;
    x = 0;
    y = 50;
    speed_x = 1;
    speed_y = 0;
    }
    
    
    public void draw(JPanel jpanel, Graphics2D g2d) {
        g2d.drawImage(image, x,y, jpanel);        
    }
    
    public void move(){
        x += speed_x;
        resetAnimation();
        changeImage();
        limits();
    }
    
    private void resetAnimation() {
        if (repeat++ > 50) {
            repeat = 0;
            //y++;
            if(image.equals(bird_lr.getImage()))
                bird_lr.getImage().flush();
            else bird_rl.getImage().flush();
        }
    }
    
    private void changeImage() {
        if(speed_x > 0) {
            image = bird_lr.getImage();
        }else {
            image = bird_rl.getImage();
        }
    }
    
    private void limits(){
        if(x == 0) {
            speed_x = 1;
        }
        else if(x == 250) {
            speed_x = -1;
        }

        if(y == 0) {
            speed_y = 1;
        }
        else if(y == 350) {
            speed_y = -1;
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
