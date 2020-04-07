package com.quentin.duck;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(){
        setTitle("pd");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(800,600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
