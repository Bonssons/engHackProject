/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enghackproject;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author shireen
 */
public class Bullet {
    int x,y;
    Image img;
    boolean visible = true;
    
    public Bullet(int startX, int startY){
        x=startX;
        y=startY;
        ImageIcon newBullet = new ImageIcon("bullet2.png");
        img = newBullet.getImage();
        visible = true;
    }
    public void draw(JPanel jpanel, Graphics2D g2d){
        g2d.drawImage(img,x,y,jpanel);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;    
    }
    public boolean getVisible(){
        return visible;
    }
    public Image getImage(){
        return img;
    }
    public void move(){
        y=y-2;
        if(x>500 || y < 0)
            visible=false;
    }
}
