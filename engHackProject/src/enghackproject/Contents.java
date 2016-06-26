/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enghackproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/**
 *
 * @author Rafael
 */
public class Contents extends JPanel implements ActionListener {

    private ArrayBlockingQueue<Integer> queue;
    private ArrayList<Bird> birds;
    private Gun slingshot;
    private Timer timer;
    private Wall wall1;
    private Wall wall2;
    private int birdsKilled;
    private int birdsToKill;
    private int level;
    private boolean levelComplete;
    private String printer;
    private int score;
    private int chances;

    Random random = new Random();
    ArrayList<Bird> toBeBorn;
    JLabel jlabel;
    JLabel jlabel2;
    
    Image imag;
    private ImageIcon background = new ImageIcon(this.getClass().getResource("background.png"));
    
    public Contents() {
        super.setDoubleBuffered(true);
        addKeyListener(new KeyInput(this));
        setFocusable(true);
        imag = background.getImage();
        requestFocusInWindow();
        level = 1;
        birdsToKill = 10;
        levelComplete = false;
        printer = "Level 1: Kill 10 birds. ";
        score = 0;
        chances = 0;
        this.jlabel = new JLabel("");
        this.jlabel2 = new JLabel("");
        configJLabel(jlabel);
        configJLabel(jlabel2);
        jlabel2.setLocation(300, 300); 
       
        birds = new ArrayList<>();
        toBeBorn = new ArrayList<>();
        birdsKilled = 0;
        int i;
        for(i = 0; i < birdsToKill/2; i++){
            birds.add(new Bird(random.nextInt(400),random.nextInt(300)));
        }
        slingshot = new Gun(250,360);
        
        queue = new ArrayBlockingQueue<>(100);
        timer = new Timer(10,this);
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
        
        if(level > 1) {
            wall1.draw(this,g2d);
            wall2.draw(this,g2d);
        }
        
        printOnDisplay(jlabel2,"Score: " + ((Integer)score).toString() + ", Wall Hits: " + (3 - chances));
        if(!printer.isEmpty()) {
            printOnDisplay(jlabel, printer);
        }else{
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
        slingshot.fireCheck();
        manageKeys();
        collision();
        bullets.removeAll(toRemove);
        toRemove.clear();
        birds.addAll(toBeBorn);
        toBeBorn.clear();
        
        if(level == 2){
            wall1.move(0);
            wall2.move(0);
        }else if(level == 3){
            wall1.move(Bird.getGeneralSpeed() - 1);
            wall2.move(Bird.getGeneralSpeed() - 1);
        }
        
        if(chances >= 3){
            level = 10;
            birdsKilled = birdsToKill;
        }
        
        if (birdsKilled == birdsToKill && !levelComplete) {
            levelComplete = true;
            birds.clear();
            bullets.clear();
            printer = "Level " + this.level + ": Complete. Press N to next level.";
            if(level == 0){ 
                printer = "Level Bonus: Complete. N/R to restart.";
            }else if(level >= 10){
                printer = "GAME OVER. Press R to restart.";
            }
        }
        repaint();
    }
    
    public void manageKeys() {
        if(queue.peek() != null){
            int key = queue.poll();
            switch(key){
                case KeyEvent.VK_SPACE:
                    slingshot.increaseBulletSpeed();
                    break;
                case KeyEvent.VK_LEFT:
                    slingshot.updateDirection(KeyEvent.VK_LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    slingshot.updateDirection(KeyEvent.VK_RIGHT);
                    break;
                case -KeyEvent.VK_SPACE:
                    if(!slingshot.isFiring()){
                        slingshot.fire();
                        slingshot.resetBulletSpeed();
                    }
                    break;
                case -KeyEvent.VK_LEFT:
                    slingshot.updateDirection(0);
                    break;
                case -KeyEvent.VK_RIGHT:
                    slingshot.updateDirection(0);
                    break;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
            
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            queue.add(KeyEvent.VK_SPACE);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            queue.add(KeyEvent.VK_LEFT);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            queue.add(KeyEvent.VK_RIGHT);
        }
    }
    
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            queue.add(-KeyEvent.VK_SPACE);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            queue.add(-KeyEvent.VK_LEFT);
        }
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            queue.add(-KeyEvent.VK_RIGHT);
        }
        if(e.getKeyCode() == KeyEvent.VK_K) {
            this.birdsKilled = birdsToKill;
        }
        if(e.getKeyCode() == KeyEvent.VK_N) {
            if(levelComplete) goToLevel(++level);
        }
        if(e.getKeyCode() == KeyEvent.VK_R) {
            this.goToLevel(1);
        }
        if(e.getKeyCode() == KeyEvent.VK_Q) {
            exit(0);
        }
    }
    
    public void goToLevel(int level) {
        Random random = new Random();
        this.wall1 = new Wall(random.nextInt(400),random.nextInt(300));
        this.wall2 = new Wall(random.nextInt(400),random.nextInt(300));
        if(level == 1){
            score = 0;
            this.level = level;
            birdsToKill = 10;
            printer = "Level 1: Kill 10 birds. ";
        }else if(level == 2){
            birdsToKill = 14;
            printer = "Level 2: Kill 14 birds. ";
        }else if(level == 3){
            birdsToKill = 20;
            printer = "Level 3: Kill 20 birds. ";
        }else if(level == 4){
            printer = "Congratulations, you win! Bonus Level. ";
            birdsToKill = 100;
            this.level = 0;
        }else if(level >= 10){
            printer = "GAME OVER. R to restart.";
            birdsToKill = -1;
        }else this.level = 0;
        birds = new ArrayList<>();
        toBeBorn = new ArrayList<>();
        birdsKilled = 0;
        chances = 0;
        Bird.resetBirdSpeed();
        levelComplete = false;
        int i;
        for(i = 0; i < birdsToKill/2; i++){
            birds.add(new Bird(random.nextInt(400),random.nextInt(300)));
        }
        slingshot = new Gun(250,360);
        queue = new ArrayBlockingQueue<>(100);
    }
    
    public void collision(){
        for(Bullet bullet: slingshot.getBullets()){
            int bullet_x = bullet.getX();
            int bullet_y = bullet.getY();
            
            for(Bird bird: birds){
                if(bird.isAlive()){
                    int bird_x = bird.getX();
                    int bird_y = bird.getY();
                    
                    if(compare(bird_x,bird_y,bullet_x,bullet_y,25,25)){
                        score++;
                        bird.die();
                        if(birdsKilled++ < birdsToKill/2){
                            toBeBorn.add(new Bird(0,random.nextInt(300)));
                        }
                        bullet.visible = false;
                    }
                }
            }
            int i;
            for(i = 0; i < 2; i++){
                int posw1_x = wall1.getX();
                int posw1_y = wall1.getY();
                int posw2_x = wall2.getX();
                int posw2_y = wall2.getY();
                
                if(compare(bullet_x-20,bullet_y,posw1_x,posw1_y,35,10)){
                    if(bullet.visible) {
                        chances++;
                    }
                    bullet.visible = false;
                }
                if(compare(bullet_x-20,bullet_y,posw2_x,posw2_y,35,10)){
                    if(bullet.visible) {
                        chances++;
                    }
                    bullet.visible = false;
                }
            }
        }
    }
    
    public boolean compare(int x1, int y1, int x2, int y2, int range_x, int range_y){
        if ((x2 - x1) < range_x && (x2 - x1) > 0 ){
            if ((y2 - y1) < range_y && (y2 - y1) > 0){
                return true;
            }
        }
        return false;
    }
    
    public void printOnDisplay(JLabel jlabel, String s) {
        jlabel.setText(s);
    }
    
    public void printClear() {
        this.remove(jlabel);
    }
    
    public void configJLabel(JLabel jlabel){
        jlabel.setFont(new Font("Verdana",1,30));
        this.add(jlabel);
        this.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
    }

}
