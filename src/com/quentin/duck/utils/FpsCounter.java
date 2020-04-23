package com.quentin.duck.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FpsCounter implements ActionListener {

    int Calls;
    int FpsLastSecond;

    public FpsCounter() {
        Timer timer = new Timer(1000, this);
        timer.start();
        Calls = 0;
        FpsLastSecond = 0;
    }

    public void AddCalls() {
        Calls++;
    }

    public int GetFPS() {
        return FpsLastSecond;
    }

    private void ResetCalls() {
        FpsLastSecond = Calls;
        Calls = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ResetCalls();
        if (FpsLastSecond != 0) {
            System.out.println("FPS:" + GetFPS());
        }
    }
}
