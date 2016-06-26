/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enghackproject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Rafael
 */
public class Contents extends JPanel implements ActionListener {
    
    private Bird bird;
    private Timer timer;

    private Image stone;
    private int posX = 0, posY = 0;
    private int speedX = 1, speedY = 1;
    private ImageIcon left_to_right = new ImageIcon(this.getClass().getResource("stone1.png"));
    private ImageIcon right_to_left = new ImageIcon(this.getClass().getResource("stone1.png"));
    
    public Contents() {
        super.setDoubleBuffered(true);
        bird = new Bird();
        timer = new Timer(10,this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if(speedX > 0) {
            stone = left_to_right.getImage();
        }else {
            stone = right_to_left.getImage();
        }
        
        bird.draw(this, g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bird.move();

        posX += speedX;
        posY=450;
        if(posX == 0) {
            speedX = 1;
        }
        else if(posX == 350) {
            speedX = -1;
        }

        repaint();
    }

        public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("SPACE PRESSED LOL");
            bird.die();
        }
    }
    
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        }
    }
}