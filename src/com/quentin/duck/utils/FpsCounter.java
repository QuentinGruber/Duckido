package com.quentin.duck.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FpsCounter implements ActionListener {
    /// Number of call this second
    public int Calls;
    /// Number of frame last second
    public int FpsLastSecond;

    public FpsCounter() {
        /// Setup timer & initialize variable 
        Timer timer = new Timer(1000, this);
        timer.start();
        Calls = 0;
        FpsLastSecond = 0;
    }

    public void AddCalls() {
        /// add one call to the current call in this second
        Calls++;
    }

    public int GetFPS() {
        /// Get last second frame count
        return FpsLastSecond;
    }

    private void ResetCalls() {
        /// Reset the current seconds call, is called after one second has elapsed
        FpsLastSecond = Calls;
        Calls = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /// Call every second
        ResetCalls();
        if (FpsLastSecond != 0) {
            System.out.println("FPS:" + GetFPS());
        }
    }
}
