package com.quentin.duck;

import com.quentin.duck.entity.Duck;
import com.quentin.duck.entity.Rocks;
import com.quentin.duck.entity.WaterLily;

import java.util.ArrayList;

public class Game {
    // Duck stuff
    public int NumberOfDucks; // number of ducks currently on the simulation
    private final int START_NUMBEROFDUCKS = 2;
    ArrayList<Duck> DuckArray = new ArrayList<>();

    // Lily stuff
    public int NumberOfLily; // number of lily currently on the simulation
    private final int START_NUMBEROFLILY = 5;
    ArrayList<WaterLily> LilyArray = new ArrayList<>();

    // Rock stuff
    public int NumberOfRocks; // number of rocks currently on the simulation
    private final int START_NUMBEROFROCKS = 3;
    ArrayList<Rocks> RocksArray = new ArrayList<>();

    private int LilySpawnChance = 10; // LilySpawnChance/1000
    private int DuckBornChance = 50; // DuckBornChance/1000
    public Game(){

        // create starting object

        if(START_NUMBEROFDUCKS > 0) {
            AddDuck(START_NUMBEROFDUCKS);
        }
        if(START_NUMBEROFLILY > 0) {
            AddLily(START_NUMBEROFLILY);
        }
        if(START_NUMBEROFROCKS > 0) {
            AddRocks(START_NUMBEROFROCKS);
        }

    }

    public void MainLoop() {
        DuckBornSystem();
        LilySpawnSystem();
        DuckMoveSystem();
    }

    public void DuckMoveSystem() {
        for(int i = 0;i < NumberOfDucks;i++ ){ // draw all ducks
            DuckArray.get(i).move();
        }
    }

    public void DuckBornSystem() {
        int nb_random = (int) (Math.random() * ((1000) + 1));
        if (nb_random < DuckBornChance) {
            AddDuck(1);
        }
    }

    public void LilySpawnSystem() {
        int nb_random = (int) (Math.random() * ((1000) + 1));
        if (nb_random < LilySpawnChance) {
            AddLily(1);
        }
    }

    private void AddDuck(int nb){
        for (int i = 0;i<=nb ;i++) {
            System.out.println("nouvo canard");
            NumberOfDucks++;
            DuckArray.add(new Duck());
        }
    }

    private void AddLily(int nb){
        for (int i = 0;i<nb ;i++) {
            System.out.println("nouvo lily");
            NumberOfLily++;
            LilyArray.add(new WaterLily());
        }
    }

    private void AddRocks(int nb){
        for (int i = 0;i<nb ;i++) {
            System.out.println("nouvo cailloux");
            NumberOfRocks++;
            RocksArray.add(new Rocks());
        }
    }


}
