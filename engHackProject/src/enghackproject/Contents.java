/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enghackproject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Rafael
 */
public class Contents extends JPanel implements ActionListener {
    
    private Image bird;
    private int posX = 50, posY = 50;
    private int speedX = 1, speedY = 1;
    private Timer timer;
    private ImageIcon bird_left_to_right = new ImageIcon(this.getClass().getResource("stone.jpg"));
    private ImageIcon bird_right_to_left = new ImageIcon(this.getClass().getResource("stone.jpg"));
    
    public Contents() {
        super.setDoubleBuffered(true);
        timer = new Timer(10,this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(speedX > 0) {
            bird = bird_left_to_right.getImage();
        }else {
            bird = bird_right_to_left.getImage();
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bird, posX,posY, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        posX += speedX;
        posY += speedY;
        
        if(posX == 0) {
            speedX = 1;
        }
        else if(posX == 250) {
            speedX = -1;
        }
        
        if(posY == 0) {
            speedY = 1;
        }
        else if(posY == 350) {
            speedY = -1;
        }
        repaint();
    }
}
