import Model.*;
import View.*;
import Controller.*;

/**
 * Klasa sluzaca powolaniu do zycia trzech obiektow, odpowiednich do wzorca projektowego tj:
 * Model, View, Controller.
 * Zawiera funkcje main, uruchamia gre.
 */
public class Uruchom {
    /**
     * Metoda tworzaca wszystkie trzy moduly gry, tj: Model, View, Controller
     *
     * @param args Paremetry wejsciowe programu, w tym przypadku nie uzywane
     */
    public static void main(String args[]) {
        Model model = new Model();
        View view = new View(model, 1024, 600, "Shooter2D");
        Controller controller = new Controller(view, model);
    }
}