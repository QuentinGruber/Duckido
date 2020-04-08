package com.quentin.duck;

import com.quentin.duck.entity.Duck;

public class Game {
    public int NumberOfDucks = 0;
    public Duck[] DuckArray = new Duck[100000];
    private int DuckBornChance = 10; // DuckBornChance/1000
    public Game(){
        AddDuck();
    }

    public void MainLoop() {
        DuckBornSystem();
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

    private void AddDuck(){
        System.out.println("nouvo canard");
        var NewDuck = new Duck();
        NumberOfDucks++;
        DuckArray[NumberOfDucks] = NewDuck;
    }
}
