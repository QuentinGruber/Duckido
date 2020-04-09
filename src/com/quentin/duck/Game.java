package com.quentin.duck;

import com.quentin.duck.entity.Duck;
import com.quentin.duck.entity.WaterLily;

public class Game {
    public int NumberOfDucks = 0;
    public Duck[] DuckArray = new Duck[100000];
    public int NumberOfLily = 0;
    public WaterLily[] LilyArray = new WaterLily[100000];
    private int LilySpawnChance = 10; // LilySpawnChance/1000
    private int DuckBornChance = 50; // DuckBornChance/1000
    public Game(){
        AddDuck();
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
            AddDuck();
        }
    }

    public void LilySpawnSystem() {
        int nb_random = (int) (Math.random() * ((1000) + 1));
        if (nb_random < LilySpawnChance) {
            AddLily();
        }
    }

    private void AddDuck(){
        System.out.println("nouvo canard");
        var NewDuck = new Duck();
        NumberOfDucks++;
        DuckArray[NumberOfDucks] = NewDuck;
    }

    private void AddLily(){
        System.out.println("nouvo canard");
        var NewLily = new WaterLily();
        NumberOfLily++;
        LilyArray[NumberOfLily] = NewLily;
    }

}
