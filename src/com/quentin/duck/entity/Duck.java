package com.quentin.duck.entity;

import com.quentin.duck.Game;
import com.quentin.duck.utils.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Duck {
    /// Duck speed
    public final int MoveSpeed;
    /// Duck index in DuckArray
    public int index;
    /// Duck position on axis X
    public int PosX;
    /// Duck position on axis Y
    public int PosY;
    /// Duck state (0: child , 1: adult etc..)
    public int State;
    /// is duck looking right ? use for orienting duck sprite
    public boolean isLookingRight;
    /// use to check if duck is alive
    public boolean isAlive;
    /// force duck to move Stationary , used for collisions
    public boolean ForceStationary;
    /// Duck current target position on axis X
    public int Target_posX = 0;
    /// Duck current target position on axis Y
    public int Target_posY = 0;
    /// use to check if duck is a leader
    public boolean isLeader;
    /// distance to the nearest lily from the current duck position
    private int NearestLily_distance;
    /// duck weight in kg
    private double Weight;
    /// duck minimal weight before he died ( change in function of the current duck state )
    private double Critical_Weight;
    /// Number of duck leader in the current simulation
    private int Leadernb;

    public Duck() { // Constructor
        /// Generate random PosX & PosY
        /// Setup starting speed/weight/state of the duck
        PosX = (int) (Math.random() * ((750) + 1));
        PosY = (int) (Math.random() * ((550) + 1));
        MoveSpeed = 1;
        Weight = 0.72; // Weight is in kg
        Critical_Weight = Weight / 1.2;
        State = 0;
        ForceStationary = false;
        isLookingRight = true;
        isAlive = true;
        isLeader = false;
        Leadernb = -1;
    }

    public void bornSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        /// Sound played when duck born
        Sound.playSound("assets/sound/coincoin.wav");
    }

    public Rectangle bounds() {
        /// return duck "hitbox" in function of his state
        if (State == 0) {
            return (new Rectangle(PosX, PosY, 40, 30));
        } else {
            return (new Rectangle(PosX, PosY, 80, 60));
        }
    }

    public void move() {
        /// Make the duck move
        if (Game.NumberOfLily != 0 && !ForceStationary) {
            LilyHunting();
        } else {
            StationaryMove();
        }
    }

    public synchronized void Eat() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        /// Called after a duck has eaten a waterlily
        /// Make the duck gain weight & play a sound
        Weight += 0.1;
        Sound.playSound("assets/sound/miam.wav");
    }

    public void Check_Weight() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        /// Check the weight of the duck
        Weight -= 0.005; // lose every fixed time
        if (Weight < Critical_Weight) { /// If his weight is lower than his critical weight the duck die
            isAlive = false;
            Game.NumberOfDucks--;
            Sound.playSound("assets/sound/dead.wav");
        }
        /// If its weight is sufficient then the duck will grow up
        else if (Weight > 0.90 && State == 0) {
            Critical_Weight = Weight / 1.3;
            State = 1;
        } else if (Weight > 1.3 && State == 1) {
            Critical_Weight = Weight / 1.4;
            State = 2;
        } else if (Weight > 1.6 && State == 2) {
            Critical_Weight = Weight / 1.5;
            State = 3;
            Whistling();
            isLeader = true;
        }
    }


    private int GetLeaderDistance() {
        /// return distance between the duck current leader and the duck current position
        return CalculateTargetDistance(Game.DuckArray.get(this.Leadernb).PosX, Game.DuckArray.get(this.Leadernb).PosY);
    }

    private void GoToTarget() {
        /// Moving to a target
        if (Leadernb != -1 && !isLeader && NearestLily_distance > GetLeaderDistance()) {
            FollowLeader();
        } else {
            GoTo(Target_posX, Target_posY);
        }
    }

    private void StationaryMove() {
        /// Moves in a stationary way
        int nb_random = (-MoveSpeed) + (int) (Math.random() * ((MoveSpeed - (-MoveSpeed)) + 1));
        PosX += nb_random;
        nb_random = (-MoveSpeed) + (int) (Math.random() * ((MoveSpeed - (-MoveSpeed)) + 1));
        PosY += nb_random;
    }

    public int CalculateTargetDistance(int x, int y) {
        /// Calculate a distance between the current duck and the given coordinate
        int Target_distance_x = (x - PosX);
        int Target_distance_y = (y - PosY);
        if (Target_distance_x < 0) {
            Target_distance_x *= -1;
        }
        if (Target_distance_y < 0) {
            Target_distance_y *= -1;
        }
        return Target_distance_x + Target_distance_y;
    }

    private void LilyHunting() {
        /// Calculates which water lily is closest to the duck and defines it as a target.
        ArrayList<Integer> NearestLily = new ArrayList<>();
        NearestLily_distance = 0;
        for (int i = 0; i < Game.LilyArray.size(); i++) {
            if (!Game.LilyArray.get(i).deleted) {
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
        /// Make the duck follow his leader
        int Leader_posX = Game.DuckArray.get(this.Leadernb).PosX;
        int Leader_posY = Game.DuckArray.get(this.Leadernb).PosY;
        GoTo(Leader_posX, Leader_posY);
    }

    private void GoTo(int target_PosX, int target_PosY) {
        /// Make the duck go to the given coordinate
        if (target_PosX < PosX) {
            isLookingRight = false;
            PosX -= MoveSpeed;
        } else if (target_PosX > PosX) {
            isLookingRight = true;
            PosX += MoveSpeed;
        }

        if (target_PosY < PosY) {
            PosY -= MoveSpeed;
        } else if (target_PosY > PosY) {
            PosY += MoveSpeed;
        }
    }

    private void Whistling() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        /// Play the Whistling sound & make the current duck the leader of all other duck in the pond
        Sound.playSound("assets/sound/Whistling.wav");
        for (int i = 0; i < Game.DuckArray.size(); i++) {
            if (!Game.DuckArray.get(i).isLeader && Game.DuckArray.get(i).Leadernb == -1) {
                Game.DuckArray.get(i).Leadernb = this.index;
            }
        }
    }
}
