/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enghackproject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Rafael
 */
public class KeyInput extends KeyAdapter{
 
    private Contents contents;
    
    public KeyInput(Contents contents) {
        this.contents = contents;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KEY PRESSED LOL");
        contents.keyPressed(e);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        contents.keyReleased(e);
    }
}
