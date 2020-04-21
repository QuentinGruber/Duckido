package com.quentin.duck.entity;

import com.quentin.duck.Game;
import com.quentin.duck.utils.Sound;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Duck {
    public int index;
    public int PosX;
    public int PosY;
    public int State; // (0: baby , 1: child etc..)
    public boolean isLookingRight;
    public boolean isAlive;
    public boolean ForceStationary;
    private int NearestLily_distance;
    private int Target_posX = 0;
    private int Target_posY = 0;
    public  int MoveSpeed;

    private double Weight;
    private double Critical_Weight;
    public boolean isLeader;
    private int Leadernb;

    public Duck() { // Constructor
        PosX = (int) (Math.random() * ((750) + 1));
        PosY = (int) (Math.random() * ((550) + 1));
        MoveSpeed = 1; // TODO: remove it DEBUG only
        Weight = 0.72 ; // Weight is in kg
        Critical_Weight = Weight / 1.2 ;
        State = 0;
        ForceStationary = false;
        isLookingRight = true;
        isAlive = true;
        isLeader = false;
        Leadernb = -1;
    }

    public void bornSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Sound.playSound("assets/sound/coincoin.wav");
    }

    public Rectangle bounds(){
        if(State == 0) {
            return (new Rectangle(PosX, PosY, 40, 30));
        }
        else{
            return (new Rectangle(PosX, PosY, 80, 60));
        }
    }

    public void move() {
        //System.out.println(Game.NumberOfLily);
        if (Game.NumberOfLily != 0 && !ForceStationary ) {
            LilyHunting();
        } else {
            StationaryMove();
        }
    }

    public synchronized void Eat() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Weight += 0.1;
        Sound.playSound("assets/sound/miam.wav");
    }

    public void Check_Weight() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Weight-= 0.005; // lose every fixed time
        //System.out.println(Weight);
        if(Weight < Critical_Weight){
            isAlive = false;
            Game.NumberOfDucks--;
            Sound.playSound("assets/sound/dead.wav");
        }
        else if (Weight > 0.90 && State == 0){
            Critical_Weight = Weight / 1.3;
            State = 1;
        }
        else if (Weight > 1.3 && State == 1){
            Critical_Weight = Weight / 1.4;
            State = 2;
        }
        else if (Weight > 1.6 && State == 2){
            Critical_Weight = Weight / 1.5;
            State = 3;
            Whistling();
            isLeader = true;
        }
    }



    private int GetLeaderDistance(){
        return CalculateTargetDistance(Game.DuckArray.get(this.Leadernb).PosX, Game.DuckArray.get(this.Leadernb).PosY);
    }

    private void GoToTarget() {

        if(Leadernb != -1 && !isLeader && NearestLily_distance > GetLeaderDistance()) {
                FollowLeader();
        }
        else{
            if (Target_posX < PosX) {
                isLookingRight = false;
                PosX -= MoveSpeed;
            } else if (Target_posX > PosX) {
                isLookingRight = true;
                PosX += MoveSpeed;
            }

            if (Target_posY < PosY) {
                PosY -= MoveSpeed;
            } else if (Target_posY > PosY) {
                PosY += MoveSpeed;
            }
        }
    }

    private void StationaryMove() {
        int nb_random = (-MoveSpeed) + (int) (Math.random() * ((MoveSpeed - (-MoveSpeed)) + 1));
        PosX += nb_random;
        nb_random = (-MoveSpeed) + (int) (Math.random() * ((MoveSpeed - (-MoveSpeed)) + 1));
        PosY += nb_random;
    }

    private int CalculateTargetDistance(int x,int y){
        int Target_distance_x = (x - PosX);
        int Target_distance_y = (y - PosY);
        if(Target_distance_x < 0){
            Target_distance_x *= -1;
        }
        if(Target_distance_y < 0){
            Target_distance_y *= -1;
        }
        return Target_distance_x + Target_distance_y;
    }
    private void LilyHunting() {
        ArrayList<Integer> NearestLily = new ArrayList<>();
        NearestLily_distance = 0;
        for (int i = 0; i < Game.LilyArray.size(); i++) {
            if(!Game.LilyArray.get(i).deleted) {
                if (NearestLily.size() == 0) { // if is first lily
                    NearestLily.add(Game.LilyArray.get(i).PosX);
                    NearestLily.add(Game.LilyArray.get(i).PosY);
                    NearestLily_distance = CalculateTargetDistance(NearestLily.get(0), NearestLily.get(1));
                } else {
                    int targetDistance = CalculateTargetDistance(Game.LilyArray.get(i).PosX, Game.LilyArray.get(i).PosY);

                    if (NearestLily_distance > targetDistance) {
                        NearestLily.set(0, Game.LilyArray.get(i).PosX);
                        NearestLily.set(1, Game.LilyArray.get(i).PosY);
                        NearestLily_distance = CalculateTargetDistance(NearestLily.get(0), NearestLily.get(1));
                    }
                }
            }
        }
        Target_posX = NearestLily.get(0);
        Target_posY = NearestLily.get(1);
        GoToTarget();
    }


    private void FollowLeader() {
        int Leader_posX = Game.DuckArray.get(this.Leadernb).PosX;
       int Leader_posY = Game.DuckArray.get(this.Leadernb).PosY;
        if (Leader_posX < PosX) {
            isLookingRight = false;
            PosX -= MoveSpeed;
        } else if (Leader_posX > PosX) {
            isLookingRight = true;
            PosX += MoveSpeed;
        }

        if (Leader_posY < PosY) {
            PosY -= MoveSpeed;
        } else if (Leader_posY > PosY) {
            PosY += MoveSpeed;
        }
    }

    private void Whistling() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Sound.playSound("assets/sound/Whistling.wav");
        for(int i = 0 ; i < Game.DuckArray.size();i++){
            if(!Game.DuckArray.get(i).isLeader && Game.DuckArray.get(i).Leadernb == -1) {
                Game.DuckArray.get(i).Leadernb = this.index;
            }
        }
    }
}
