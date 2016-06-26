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

    private ArrayList<Bird> birds;
    private Gun slingshot;
    private Timer timer;
    Random random = new Random();

    Image imag;
    private Image stone;
    private ImageIcon background = new ImageIcon(this.getClass().getResource("background.png"));
    public Contents() {
        super.setDoubleBuffered(true);
        addKeyListener(new KeyInput(this));
        setFocusable(true);
        imag = background.getImage();
        requestFocusInWindow();
        
        birds = new ArrayList<>();
        int i;
        for(i = 0; i < random.nextInt(10)+2; i++){
            birds.add(new Bird(random.nextInt(400),random.nextInt(300)));
        }
        slingshot = new Gun(250,360);
        timer = new Timer(10,this);
        timer.start();
        
        timer.start();   
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(imag, 0, 0, null);
        for(Bird bird: birds) {
            bird.draw(this, g2d);
        }

        slingshot.draw(this,g2d);
        
        ArrayList<Bullet> bullets = slingshot.getBullets();
        for(Bullet bullet: bullets){
            bullet.draw(this,g2d);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Bullet> bullets = slingshot.getBullets();
        ArrayList<Bullet> toRemove = new ArrayList<>();
        for(Bullet bullet: bullets){
            if(bullet.getVisible())
                bullet.move();
            else {
                toRemove.add(bullet);
            }
        }
        
        for(Bird bird: birds){
            bird.move();
        }
        slingshot.move();
        
        collision();
        bullets.removeAll(toRemove);
        toRemove.clear();
        repaint();
    }

        public void keyPressed(KeyEvent e) {
            
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            if(!slingshot.isFiring()){
                slingshot.fire();
                slingshot.setFiring(true);
            }
            slingshot.FireCount();
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
            if(slingshot.isFiring()){
                slingshot.stop_firing();
                slingshot.setFiring(false);
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            slingshot.updateDirection(0);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            slingshot.updateDirection(0);
        }
    }
    
    public void collision(){
        
        for(Bird bird: birds){
            if(bird.isAlive()){
                int bird_x = bird.getX();
                int bird_y = bird.getY();
                for(Bullet bullet: slingshot.getBullets()){
                    int bullet_x = bullet.getX();
                    int bullet_y = bullet.getY();
                
                    if(compare(bird_x,bird_y,bullet_x,bullet_y)){
                        bird.die();
                        bullet.visible = false;
                    }
                }
            }
        }
    }
    
    public boolean compare(int x1, int y1, int x2, int y2){
        int range = 25;
        if ((x2 - x1) < range && (x2 - x1) > 0 ){
            if ((y2 - y1) < range && (y2 - y1) > 0){
                return true;
            }
        }
        return false;
    }
}
