package com.quentin.duck;

import java.io.IOException;

public class Launcher {
    public Launcher() throws IOException {
        /// Launch the simulation
        new SimulationWindow();
    }

    public static void main(String[] args) throws IOException {
        new Launcher();
    }
}
