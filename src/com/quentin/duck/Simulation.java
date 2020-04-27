package com.quentin.duck;

import com.quentin.duck.entity.Duck;
import com.quentin.duck.entity.Rocks;
import com.quentin.duck.entity.WaterLily;
import com.quentin.duck.utils.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Simulation {
    // Duck stuff
    /// Number of ducks currently on the simulation
    public static int NumberOfDucks;
    /// Array that contain all duck object in the simulation
    public static ArrayList<Duck> DuckArray = new ArrayList<>();
    // Lily stuff
    /// Number of Lily currently on the simulation
    public static int NumberOfLily; // number of lily currently on the simulation
    /// Array that contain all WaterLily object in the simulation
    public static ArrayList<WaterLily> LilyArray = new ArrayList<>();
    /// Array that contain all Rocks object in the simulation
    public static ArrayList<Rocks> RocksArray = new ArrayList<>();
    /// Number of frames (used to call random stuff only once per maxfps so that the randomness is not affected by the performance of the simulation)
    private static int count_frame;
    /// Number of ducks at the beginning of the simulation
    private final int START_NUMBEROFDUCKS = 2;
    /// Max number of ducks in the simulation
    private final int MAX_NUMBEROFDUCKS = 25;
    ///  Number of waterlily at the beginning of the simulation
    private final int START_NUMBEROFLILY = 1;
    /// Max number of waterlily in the simulation
    private final int MAX_NUMBEROFLILY = 0;
    ///  Number of rocks at the beginning of the simulation
    private final int START_NUMBEROFROCKS = 2;
    /// Chance for a lily to spawn every 1 second (LILY_SPAWN_CHANCE/1000)
    private final int LILY_SPAWN_CHANCE = 500; // LILY_SPAWN_CHANCE/1000
    /// Chance for a duck to spawn every 1 second (DUCK_BORN_CHANCE/1000)
    private final int DUCK_BORN_CHANCE = 100; // DUCK_BORN_CHANCE/1000
    // Rock stuff
    /// Number of rocks currently on the simulation
    public int NumberOfRocks;

    public Simulation() {
    

        /// Create starting object

        if (START_NUMBEROFDUCKS > 0) {
            Thread Add_START_NUMBEROFDUCKS = new Thread(() -> AddDuck(START_NUMBEROFDUCKS));
            Add_START_NUMBEROFDUCKS.start();
        }
        if (START_NUMBEROFLILY > 0) {
            Thread Add_START_NUMBEROFLILY = new Thread(() -> AddLily(START_NUMBEROFLILY));
            Add_START_NUMBEROFLILY.start();
        }
        if (START_NUMBEROFROCKS > 0) {
            Thread Add_START_NUMBEROFROCKS = new Thread(() -> AddRocks(START_NUMBEROFROCKS));
            Add_START_NUMBEROFROCKS.start();
        }

    }

    public void MainLoop() {
        /// Main loop of the simulation
        count_frame++;
        if (count_frame > SimulationPanel.MaxFPS) {
            count_frame = 0;
            DuckBornSystem();
            LilySpawnSystem();
            DuckWeightSystem();
        }
        DuckMoveSystem();
        CheckCollid();
    }

    public void CheckCollid() {
        /// Check collision of all element in the simulation
        int collide_setback = 20;
        for (int i = 0; i < DuckArray.size(); i++) { // for all duck
            if (DuckArray.get(i).isAlive) {
                DuckArray.get(i).ForceStationary = false;
                for (int j = 0; j < RocksArray.size(); j++) { // collision with rocks
                    Rectangle duck = DuckArray.get(i).bounds();
                    Rectangle rock = RocksArray.get(j).bounds();
                    if (duck.intersects(rock)) {
                        if (DuckArray.get(i).Target_posX < DuckArray.get(i).PosX) {
                            DuckArray.get(i).PosX += collide_setback;
                            DuckArray.get(i).PosY += collide_setback * 2;
                        } else {
                            DuckArray.get(i).PosX -= collide_setback;
                            DuckArray.get(i).PosY -= collide_setback * 2;
                        }
                    }
                }
                for (int j = 0; j < DuckArray.size(); j++) { // collision between ducks
                    if (DuckArray.get(j).isAlive && i != j) {
                        Rectangle duck = DuckArray.get(i).bounds();
                        Rectangle otherduck = DuckArray.get(j).bounds();
                        if (duck.intersects(otherduck)) {
                            if (!DuckArray.get(i).isLeader && !DuckArray.get(j).isLeader) {
                                check_duck_priority(i, j);
                            } else if (!DuckArray.get(j).isLeader) {
                                DuckArray.get(j).ForceStationary = true;
                            } else if (!DuckArray.get(i).isLeader) {
                                DuckArray.get(i).ForceStationary = true;
                            } else {
                                check_duck_priority(i, j);
                            }
                        }
                    }
                }
                // check if is colliding with any lily
                if (NumberOfLily > 0) {
                    for (int j = 0; j < LilyArray.size(); j++) {
                        if (!LilyArray.get(j).deleted) {
                            Rectangle duck = DuckArray.get(i).bounds();
                            Rectangle lily = LilyArray.get(j).bounds();
                            if (duck.intersects(lily)) {
                                NumberOfLily--;
                                LilyArray.get(j).deleted = true;
                                int finalI = i;
                                Thread eating = new Thread(() -> {
                                    try {
                                        DuckArray.get(finalI).Eat();
                                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                                        e.printStackTrace();
                                    }
                                });
                                eating.start();
                            }
                        }
                    }
                }
            }
        }
    }

    private void check_duck_priority(int i, int j) {
        /// Called after the collision of two ducks, calculate which one is closest to its target to give it priority.
        if (DuckArray.get(i).CalculateTargetDistance(DuckArray.get(i).Target_posX,
                DuckArray.get(i).Target_posY) < DuckArray.get(j).CalculateTargetDistance(DuckArray.get(j).Target_posX,
                DuckArray.get(j).Target_posY)) {
            DuckArray.get(j).ForceStationary = true;
        } else {
            DuckArray.get(i).ForceStationary = true;
        }
    }

    public void DuckWeightSystem() {
        /// Check Weight of all ducks
        Thread DuckWeightSystem = new Thread(() -> {
            for (int i = 0; i < DuckArray.size(); i++) { // draw all ducks
                if (DuckArray.get(i).isAlive) {
                    try {
                        DuckArray.get(i).Check_Weight();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        DuckWeightSystem.start();
    }

    public void DuckMoveSystem() { // execute move() of all ducks
        /// Make all ducks move
        Thread DuckMoveSystem = new Thread(() -> {
            for (int i = 0; i < DuckArray.size(); i++) { // draw all ducks
                if (DuckArray.get(i).isAlive) {
                    DuckArray.get(i).move();
                }
            }
        });
        DuckMoveSystem.start();
    }

    public void DuckBornSystem() {
        /// Randomly spawn a new duck
        if (Random.chance(DUCK_BORN_CHANCE, 1000)) {
            Thread DuckBornSystem = new Thread(() -> AddDuck(1));
            DuckBornSystem.start();
        }
    }

    public void LilySpawnSystem() {
        /// Randomly spawn a new WaterLily
        if (Random.chance(LILY_SPAWN_CHANCE, 1000)) {
            Thread LilySpawnSystem = new Thread(() -> AddLily(1));
            LilySpawnSystem.start();
        }
    }

    private void AddDuck(int nb) {
        /// Add a new duck to the simulation (if the maximum number of ducks is not reached).
        if (MAX_NUMBEROFDUCKS == 0 || MAX_NUMBEROFDUCKS > NumberOfDucks)
            for (int i = 0; i < nb; i++) {
                NumberOfDucks++;
                Duck duck = new Duck();
                duck.index = DuckArray.size();
                DuckArray.add(duck);
                try {
                    duck.bornSound();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
            }
    }

    private void AddLily(int nb) {
        /// Add a new WaterLily to the simulation (if the maximum number of WaterLily is not reached).
        if (MAX_NUMBEROFLILY == 0 || MAX_NUMBEROFLILY > NumberOfLily) {
            for (int i = 0; i < nb; i++) {
                NumberOfLily++;
                LilyArray.add(new WaterLily());
            }
        }
    }

    private void AddRocks(int nb) {
        /// Add a new Rock to the simulation
        for (int i = 0; i < nb; i++) {
            NumberOfRocks++;
            RocksArray.add(new Rocks());
        }
    }


}
