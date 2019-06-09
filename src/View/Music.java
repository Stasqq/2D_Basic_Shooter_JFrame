package View;

import java.util.ArrayList;

/**
 * Odtwarzacz muzyki.
 * Oddzielny watek.
 * Odtwarzanie odbywa sie w sposob zapetlony wszystkiech podanych plikow muzycznych.
 */
public class Music implements Runnable {

    /**
     * Lista plikow do odtwarzania
     */
    private ArrayList<AudioFile> musicFiles;
    /**
     * Index aktualnie odtwarzanego pliku
     */
    private int currentSongIndex;
    /**
     * Zmienna mowiaca o aktualnym odtwarzaniu
     */
    private boolean running;

    /**
     * Odtwarzacz muzyki.
     * Oddzielny watek.
     *
     * @param files Lista nazw plikow, ktore maja byc odtwarzane(odtwarzane beda w kolejnosci zgodnej z ta lista)
     */
    public Music(String... files) {
        musicFiles = new ArrayList<AudioFile>();
        for (String file : files)
            musicFiles.add(new AudioFile("res\\" + file));
    }

    /**
     * Odtwarza zapetlona liste plikow muzycznych.
     * Nadpisana metoda dla watku.
     */
    public void run() {
        running = true;
        AudioFile song = musicFiles.get(currentSongIndex);
        song.play();

        while (running) {
            if (!song.isPlaying()) {
                currentSongIndex++;
                if (currentSongIndex >= musicFiles.size())
                    currentSongIndex = 0;
                song = musicFiles.get(currentSongIndex);
                song.play();
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
