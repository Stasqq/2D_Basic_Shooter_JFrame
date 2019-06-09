package View;

import javax.sound.sampled.*;
import java.io.File;

/**
 * Reprezentacja pliku muzycznego, ktory odtwarzany bedzie w tle.
 */
public class AudioFile implements LineListener {
    /**
     * Plik z muzyka
     */
    private File soundFile;
    /**
     * Strumien wejsciowy audio
     */
    private AudioInputStream ais;
    /**
     * Format obslugiwany przez strumien
     */
    private AudioFormat format;
    /**
     * Zbior informacji o danych
     */
    private DataLine.Info info;
    /**
     * Buffer danych pliku muzycznego
     */
    private Clip clip;
    /**
     * Zmienna decydujaca o glosnosci muzyki
     */
    private FloatControl gainControl;
    /**
     * Zmienna ktora przy zmianie na false informuje o zakonczeniu odtwarzania
     */
    private volatile boolean playing;

    /**
     * Reprezentacja pliku muzycznego, ktory odtwarzany bedzie w tle.
     *
     * @param fileName Nazwa pliku muzycznego
     */
    public AudioFile(String fileName) {
        soundFile = new File(fileName);

        try {
            ais = AudioSystem.getAudioInputStream(soundFile);
            format = ais.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.addLineListener(this);
            clip.open(ais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Metoda rozpoczynajaca odtwarzanie, z domyslnym ustawieniem glosnosci -10
     */
    public void play() {
        play(-10);
    }

    /**
     * Metoda rozpoczynajaca odtwarzanie, z zadanym parametrem glosnosci
     *
     * @param volume Parametr glosnosci odtwarzania(dla odtwarzania domyslengo powinno byc 0)
     */
    public void play(float volume) {
        gainControl.setValue(volume);
        clip.start();
        playing = true;
    }

    public boolean isPlaying() {
        return playing;
    }

    /**
     * Metoda obslugujaca zdarzenia typu rozpoczecia i zakonczenia odtwarzania pliku muzycznego.
     *
     * @param event Zdarzenie ktore nalezy obsluzyc
     */
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.START)
            playing = true;
        else if (event.getType() == LineEvent.Type.STOP) {
            clip.stop();
            clip.flush();
            clip.setFramePosition(0);
            playing = false;
        }
    }
}
