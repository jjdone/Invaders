package engine;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

    //define storage for start position
    Long nowFrame;
    Clip clip;

    // get the clip status
    String thestatus;

    AudioInputStream audioStream;

    static String thePath;

    // initialize both the clip and streams
    public SoundPlayer(String music)
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        // the input stream object
        audioStream =
                AudioSystem.getAudioInputStream(
                        new File(music)
                                .getAbsoluteFile());

        // the reference to the clip
        clip = AudioSystem.getClip();

        clip.open(audioStream);

    }
    public void play()
    {
        //start the clip
        clip.start();

        thestatus = "play";
    }
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        nowFrame = 0L;
        clip.stop();
        clip.close();
    }
}


