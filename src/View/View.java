package View;

import Controller.*;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * Czesc projektu odpowiedzialna za wyswietlanie i kontakt z uzytkownikiem
 */
public class View {
    /**
     * Okno do wyswietlnia gry
     */
    private JFrame frame;
    /**
     * Odnosnik do modelu gry
     */
    private Model model;
    /**
     * Grafika przechowujaca mape gry
     */
    private BufferedImage map = null;
    /**
     * Grafika przechowujaca elementy do rysowania obiektow
     */
    private BufferedImage spriteSheet = null;
    /**
     * Odnoscik do obiektu przechowujacego plik graficzny, z ktorego potem rysujemy obiekty
     */
    private SpriteSheet ss;
    /**
     * Zmienna reprezentujaca przyblizona ilosc zycia gracza, decyduje ona o ilosci wyswietlanych serc w interfejsie
     */
    private int hp;
    /**
     * Grafika ktora uzupelniana jest podloga w grze
     */
    private BufferedImage floor = null;
    /**
     * Tablica grafik reprezentujacych rozne stany zdrowia gracza
     */
    private BufferedImage hp_image[] = new BufferedImage[6];

    /**
     * Czesc projektu odpowiedzialna za wyswietlanie i kontakt z uzytkownikiem
     *
     * @param model  Odnosnik do modelu gry
     * @param width  Szerokosc okna
     * @param height Wysokosc okna
     * @param title  Tytul okna
     */
    public View(Model model, int width, int height, String title) {
        this.model = model;
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Metoda laczaca controller z JFramem
     *
     * @param controller Odnosnik do controllera gry
     */
    public void addController(Controller controller) {
        frame.add(controller);
    }

    /**
     * Metoda renderujaca gre.
     * Ustawienia wartosci typu BufferStrategy. Wyrysowanie podstawowego interfejsu dla gracza.
     *
     * @param controller Odnosnik do controllera gry
     */
    public void render(Controller controller) {
        BufferStrategy bs = controller.getBufferStrategy();
        if (bs == null) {
            controller.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2D = (Graphics2D) g;

        /////////////////////////////////////////////////////////////////
        // Tu zaczyna sie rysowanie

        g2D.translate(-model.getCameraX(), -model.getCameraY());

        for (int xx = 0; xx < 30 * 72; xx += 32) {
            for (int yy = 0; yy < 30 * 72; yy += 32) {
                g.drawImage(floor, xx, yy, null);
            }
        }

        model.getHandler().render(g);

        g2D.translate(model.getCameraX(), model.getCameraY());

        hp = model.getPlayerHp();
        if (hp > 40) {
            hp = hp / 40;
            hp = hp * 40;
        } else if (hp > 0 && hp < 40) {
            hp = 40;
        } else {
            hp = 0;
        }

        switch (hp) {
            case 40: {
                g.drawImage(hp_image[1], 5, 5, null);
            }
            break;
            case 80: {
                g.drawImage(hp_image[2], 5, 5, null);
            }
            break;
            case 120: {
                g.drawImage(hp_image[3], 5, 5, null);
            }
            break;
            case 160: {
                g.drawImage(hp_image[4], 5, 5, null);
            }
            break;
            case 200: {
                g.drawImage(hp_image[5], 5, 5, null);
            }
            break;
            default: {
                g.drawImage(hp_image[0], 5, 5, null);
            }
            break;
        }

        g.setColor(Color.WHITE);
        g.drawString("Amunicja: " + model.getPlayerAmmo(), 10, 50);
        g.drawString("Punkty: " + model.getPlayerGold(), 10, 65);

        // Tu konczy sie rysowanie
        /////////////////////////////////////////////////////////////////

        g.dispose();
        bs.show();
    }

    /**
     * Stworzenie poziomu, wczytanie danych poczatkowych, nadanie poczatkowych wartosci
     */
    public void loadMap() {
        BufferedImageLoader loader = new BufferedImageLoader();
        map = loader.loadImage("/game_map.png");

        spriteSheet = loader.loadImage("/images.png");

        ss = new SpriteSheet(spriteSheet);

        floor = ss.grabImage(2, 1, 32, 32);
        hp_image[0] = ss.grabImage(9, 2, 192, 32);
        hp_image[1] = ss.grabImage(9, 1, 192, 32);
        hp_image[2] = ss.grabImage(15, 4, 192, 32);
        hp_image[3] = ss.grabImage(15, 3, 192, 32);
        hp_image[4] = ss.grabImage(15, 2, 192, 32);
        hp_image[5] = ss.grabImage(15, 1, 192, 32);
        loadLevel(map);
    }

    /**
     * Odczytanie danych z grafiki, na ktorych podstawie stworzymy poziom gry i wszystkie znajdujace sie na nim poczatkowo obiekty.
     *
     * @param image Grafika z ktorej odczytywac bedziemy polozenie obiektow
     */
    private void loadLevel(BufferedImage image) {
        int width = image.getHeight();
        int height = image.getHeight();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 0 && blue == 0) {
                    model.getHandler().addObject(new Block(xx * 32, yy * 32, ID.Block, ss));
                }

                if (blue == 255 && green == 0 && red == 0) {
                    Player pl = new Player(xx * 32, yy * 32, ID.Player, model.getHandler(), ss);
                    model.setPlayer(pl);
                    model.getHandler().addObject(pl);

                }

                if (green == 255 && blue == 0 && red == 0) {
                    model.getHandler().addObject(new Enemy(xx * 32, yy * 32, ID.Enemy, model.getHandler(), ss));
                }

                if (green == 255 && blue == 255 && red == 0) {
                    model.getHandler().addObject(new Crate(xx * 32, yy * 32, ID.Create, ss));
                }

                if (blue == 255 && red == 255 && green == 0) {
                    model.getHandler().addObject(new Boss(xx * 32, yy * 32, ID.Enemy, model.getHandler(), ss));
                }
            }
        }
    }

    /**
     * Metoda zwracajaca obiekt przechowujacy grafike zawierajaca wszystkie grafiki obiektow gry
     *
     * @return Obiekt przechowujacy grafike gry
     */
    public SpriteSheet getSpriteSheet() {
        return ss;
    }
}
