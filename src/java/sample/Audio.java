package sample;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio {

    private final String track;
    private FloatControl volumeC = null;
    private double wt;

    public Audio(String track) {
        this.track = track;
    }

    public void sound() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File(this.track);

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        clip.setFramePosition(0);
        clip.start();
    }

    public void setVolume() {
        if (wt < 0) wt = 0;
        if (wt > 1) wt = 1;
        float min = volumeC.getMinimum();
        float max = volumeC.getMaximum();
        volumeC.setValue((max - min) * (float)wt + min);
    }
}
