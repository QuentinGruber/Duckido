package com.quentin.duck.utils;

public class Random {

    public static boolean chance(int nb_chance, int total_nb_chance) {
        int nb_random = (int) (Math.random() * ((total_nb_chance) + 1));
        return nb_random < nb_chance;
    }
}
