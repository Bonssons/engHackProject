/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enghackproject;

import javax.swing.JFrame;

/**
 *
 * @author Rafael
 */
class Screen extends JFrame{
    
    public void Init(){
        super.setTitle("Bird Hunt");
        super.setSize(300,400);
        super.setResizable(false);
        super.add(new Contents());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    public Screen() {
        Init();
    }
    
}
