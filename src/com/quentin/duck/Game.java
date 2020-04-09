package com.quentin.duck;

import com.quentin.duck.entity.Duck;
import com.quentin.duck.entity.Rocks;
import com.quentin.duck.entity.WaterLily;
import com.quentin.duck.utils.Random;

import java.util.ArrayList;

public class Game {
    // Duck stuff
    public static int NumberOfDucks; // number of ducks currently on the simulation
    private final int START_NUMBEROFDUCKS = 1;
    private final int MAX_NUMBEROFDUCKS = 1;
    public static ArrayList<Duck> DuckArray = new ArrayList<>();

    // Lily stuff
    public static int NumberOfLily; // number of lily currently on the simulation
    private final int START_NUMBEROFLILY = 1;
    private final int MAX_NUMBEROFLILY = 0;

    public static ArrayList<WaterLily> LilyArray = new ArrayList<>();

    // Rock stuff
    public int NumberOfRocks; // number of rocks currently on the simulation
    private final int START_NUMBEROFROCKS = 3;
    public static ArrayList<Rocks> RocksArray = new ArrayList<>();

    private final int LILY_SPAWN_CHANCE = 1000; // LILY_SPAWN_CHANCE/1000
    private final int DUCK_BORN_CHANCE = 100; // DUCK_BORN_CHANCE/1000

    public Game() {

        // create starting object

        if (START_NUMBEROFDUCKS > 0) {
            AddDuck(START_NUMBEROFDUCKS);
        }
        if (START_NUMBEROFLILY > 0) {
            AddLily(START_NUMBEROFLILY);
        }
        if (START_NUMBEROFROCKS > 0) {
            AddRocks(START_NUMBEROFROCKS);
        }

    }

    public void MainLoop() {
        DuckBornSystem();
        LilySpawnSystem();
        DuckMoveSystem();
    }

    public void DuckMoveSystem() { // execute move() of all ducks
        for (int i = 0; i < NumberOfDucks; i++) { // draw all ducks
            DuckArray.get(i).move();
        }
    }

    public void DuckBornSystem() {
        if (Random.chance(DUCK_BORN_CHANCE, 1000 * GamePanel.CurrentFPS)) {
            AddDuck(1);
        }
    }

    public void LilySpawnSystem() {
        System.out.println("Lili chances : " + (1000 * GamePanel.CurrentFPS));
        if (Random.chance(LILY_SPAWN_CHANCE, 1000 * GamePanel.CurrentFPS)) {
            AddLily(1);
        }
    }

    private void AddDuck(int nb) {
        if (MAX_NUMBEROFDUCKS == 0 || MAX_NUMBEROFDUCKS > NumberOfDucks)
            for (int i = 0; i < nb; i++) {
                System.out.println("nouvo canard");
                NumberOfDucks++;
                DuckArray.add(new Duck());
            }
    }

    private void AddLily(int nb) {
        if (MAX_NUMBEROFLILY == 0 || MAX_NUMBEROFLILY > NumberOfLily) {
            for (int i = 0; i < nb; i++) {
                System.out.println("nouvo lily");
                NumberOfLily++;
                LilyArray.add(new WaterLily());
            }
        }
    }

    private void AddRocks(int nb) {
        for (int i = 0; i < nb; i++) {
            System.out.println("nouvo cailloux");
            NumberOfRocks++;
            RocksArray.add(new Rocks());
        }
    }


}
