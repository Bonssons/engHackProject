
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enghackproject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Rafael
 */
public class Contents extends JPanel implements ActionListener {
    private Stone ston;
    private Bird bird;
    private Timer timer;

    private Image stone;
    public Contents() {
        super.setDoubleBuffered(true);
        addKeyListener(new KeyInput(this));
        setFocusable(true);
        requestFocusInWindow();
        
        ston = new Stone(50,450);
        bird = new Bird(0,50);
        timer = new Timer(10,this);
        timer.start();
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        
        
        //g2d.drawImage(stone, posX,posY, this);
        bird.draw(this, g2d);
        ston.draw(this, g2d);
        ArrayList bullets = Stone.getBullets();
        for(int z=0;z<bullets.size();z++){
            Bullet m = (Bullet) bullets.get(z);
            g2d.drawImage(m.getImage(),m.getX(),m.getY(),null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList bullets = Stone.getBullets();
        for(int z=0;z<bullets.size();z++){
            Bullet m = (Bullet) bullets.get(z);
            if(m.getVisible()==true)
            m.move();
            else
                bullets.remove(z);
        }
        bird.move();
        ston.move();
        repaint();
        
    }

        public void keyPressed(KeyEvent e) {
            
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.die();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            ston.fire();
        }
    }
    
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        }
    }
}