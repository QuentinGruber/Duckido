package com.quentin.duck;

import com.quentin.duck.entity.Duck;
import com.quentin.duck.entity.Rocks;
import com.quentin.duck.entity.WaterLily;
import com.quentin.duck.utils.Random;

import java.util.ArrayList;

public class Game {
    // Duck stuff
    public static int NumberOfDucks; // number of ducks currently on the simulation
    private final int START_NUMBEROFDUCKS = 20;
    private final int MAX_NUMBEROFDUCKS = 0;
    public static ArrayList<Duck> DuckArray = new ArrayList<>();

    // Lily stuff
    public static int NumberOfLily; // number of lily currently on the simulation
    private final int START_NUMBEROFLILY = 20;
    private final int MAX_NUMBEROFLILY = 0;

    public static ArrayList<WaterLily> LilyArray = new ArrayList<>();

    // Rock stuff
    public int NumberOfRocks; // number of rocks currently on the simulation
    private final int START_NUMBEROFROCKS = 3;
    public static ArrayList<Rocks> RocksArray = new ArrayList<>();

    private final int LILY_SPAWN_CHANCE = 100; // LILY_SPAWN_CHANCE/1000
    private final int DUCK_BORN_CHANCE = 100; // DUCK_BORN_CHANCE/1000

    public Game() {

        // create starting object

        if (START_NUMBEROFDUCKS > 0) {
            Thread Add_START_NUMBEROFDUCKS = new Thread(new Runnable() {
                @Override
                public void run() {
                    AddDuck(START_NUMBEROFDUCKS);
                }
            });
            Add_START_NUMBEROFDUCKS.start();
        }
        if (START_NUMBEROFLILY > 0) {
            Thread Add_START_NUMBEROFLILY = new Thread(new Runnable() {
                @Override
                public void run() {
                    AddLily(START_NUMBEROFLILY);
                }
            });
            Add_START_NUMBEROFLILY.start();
        }
        if (START_NUMBEROFROCKS > 0) {
            Thread Add_START_NUMBEROFROCKS = new Thread(new Runnable() {
                @Override
                public void run() {
                    AddRocks(START_NUMBEROFROCKS);
                }
            });
            Add_START_NUMBEROFROCKS.start();
        }

    }

    public void MainLoop() {
        DuckBornSystem();
        LilySpawnSystem();
        DuckMoveSystem();
        DuckWeightSystem();
    }

    public void DuckWeightSystem() { // execute move() of all ducks
        Thread DuckWeightSystem = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < NumberOfDucks; i++) { // draw all ducks
                    DuckArray.get(i).Check_Weight();
                }
            }
        });
        DuckWeightSystem.start();
    }

    public void DuckMoveSystem() { // execute move() of all ducks
        Thread DuckMoveSystem = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < NumberOfDucks; i++) { // draw all ducks
                    DuckArray.get(i).move();
                }
            }
        });
        DuckMoveSystem.start();
    }

    public void DuckBornSystem() {
        if (Random.chance(DUCK_BORN_CHANCE, 1000 * GamePanel.CurrentFPS)) {
            Thread DuckBornSystem = new Thread(new Runnable() {
                @Override
                public void run() {
                    AddDuck(1);
                }
            });
            DuckBornSystem.start();
        }
    }

    public void LilySpawnSystem() {
        if (Random.chance(LILY_SPAWN_CHANCE, 1000 * GamePanel.CurrentFPS)) {
            Thread LilySpawnSystem = new Thread(new Runnable() {
                @Override
                public void run() {
                    AddLily(1);
                }
            });
            LilySpawnSystem.start();
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
