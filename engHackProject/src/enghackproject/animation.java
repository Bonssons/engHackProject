/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enghackproject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author shireen
 */
public class animation extends JPanel {
    
    private Image stone;
    
    public animation(){
        super.setDoubleBuffered(true);
    }
  
    public void component(Graphics g){
        ImageIcon i = new ImageIcon(this.getClass().getResource("stone.jpg"));
        stone = i.getImage();
        Graphics2D g2d  = (Graphics2D)g;
        g2d.drawImage(stone,100,100,this);
}
    
}
