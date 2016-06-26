
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.move();
        ston.move();
        repaint();
    }

        public void keyPressed(KeyEvent e) {
            
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.die();
        }
    }
    
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        }
    }
}