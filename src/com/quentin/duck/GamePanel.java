package com.quentin.duck;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements  Runnable {

    public static int width;
    public static int height;

    private Thread thread;

    public GamePanel(int width , int height){
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();
    }
    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this,"GameThread");
            thread.start();
        }
    }
    public void run(){

    }
}
