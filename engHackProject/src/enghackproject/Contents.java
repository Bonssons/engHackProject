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
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Rafael
 */
public class Contents extends JPanel implements ActionListener {
    private Stone ston;
    private ArrayList<Bird> birds;
    private Gun slingshot;
    private Timer timer;
    Random random = new Random();

    private Image stone;
    
    public Contents() {
        super.setDoubleBuffered(true);
        addKeyListener(new KeyInput(this));
        setFocusable(true);
        requestFocusInWindow();
        
        ston = new Stone(50,450);
        birds = new ArrayList<>();
        int i;
        for(i = 0; i < random.nextInt(10)+2; i++){
            birds.add(new Bird(random.nextInt(400),random.nextInt(300)));
        }
        slingshot = new Gun(250,540);
        timer = new Timer(10,this);
        timer.start();
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        
        
        //g2d.drawImage(stone, posX,posY, this);
        for(Bird bird: birds) {
            bird.draw(this, g2d);
        }
        //ston.draw(this, g2d);
        slingshot.draw(this,g2d);
        
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
        
        for(Bird bird: birds){
            bird.move();
        }
        ston.move();
        slingshot.move();
        repaint();
    }

        public void keyPressed(KeyEvent e) {
            
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            slingshot.fire();
        }
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            slingshot.updateDirection(KeyEvent.VK_LEFT);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            slingshot.updateDirection(KeyEvent.VK_RIGHT);
        }
    }
    
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            slingshot.stop_firing();
        }
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            slingshot.updateDirection(0);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            slingshot.updateDirection(0);
        }
    }
}