/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enghackproject;
import java.awt.*;
import javax.swing.ImageIcon;
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
        if(x>500)
            visible=false;
    }
    
    
}
