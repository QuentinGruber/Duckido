package com.quentin.duck;

import com.quentin.duck.entity.Duck;
import com.quentin.duck.entity.Rocks;
import com.quentin.duck.entity.WaterLily;

public class Game {
    // Duck stuff
    public int NumberOfDucks; // number of ducks currently on the simulation
    private final int START_NUMBEROFDUCKS = 2;
    public Duck[] DuckArray = new Duck[100000];

    // Lily stuff
    public int NumberOfLily; // number of lily currently on the simulation
    private final int START_NUMBEROFLILY = 5;
    public WaterLily[] LilyArray = new WaterLily[100000];

    // Rock stuff
    public int NumberOfRocks; // number of rocks currently on the simulation
    private final int START_NUMBEROFROCKS = 3;
    public Rocks[] RocksArray = new Rocks[START_NUMBEROFROCKS+1];

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
        for(int i = 1;i <= NumberOfDucks;i++ ){ // draw all ducks
        DuckArray[i].move();
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
        for (int i = 0;i<nb ;i++) {
            System.out.println("nouvo canard");
            var NewDuck = new Duck();
            NumberOfDucks++;
            DuckArray[NumberOfDucks] = NewDuck;
        }
    }

    private void AddLily(int nb){
        for (int i = 0;i<nb ;i++) {
            System.out.println("nouvo lily");
            var NewLily = new WaterLily();
            NumberOfLily++;
            LilyArray[NumberOfLily] = NewLily;
        }
    }

    private void AddRocks(int nb){
        for (int i = 0;i<nb ;i++) {
            System.out.println("nouvo cailloux");
            var NewRock = new Rocks();
            NumberOfRocks++;
            RocksArray[NumberOfRocks] = NewRock;
        }
    }


}
