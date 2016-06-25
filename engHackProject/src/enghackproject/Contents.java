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
        
        bird.draw(this, g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        bird.move();
        repaint();
    }
}
