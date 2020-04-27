package com.quentin.duck;

import java.io.IOException;

public class Launcher {
    public Launcher() throws IOException {
        /// Launch the simulation
        new GameWindow();
    }

    public static void main(String[] args) throws IOException {
        new Launcher();
    }
}
