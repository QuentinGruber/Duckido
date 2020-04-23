package com.quentin.duck.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {


    public static void playSound(String soundFile) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File f = new File("./" + soundFile);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }

}
