import Model.*;
import View.*;
import Controller.*;

/**
 * / Klasa służaca powołaniu do życia trzech obiektów
 * / Odpowiednich do wzorca projektowego
 * / Model, View, Controller
 * / Zawiera funkcję main, uruchamia grę.
 * /
 */

public class Uruchom {
    /**
     * funkcja main calej gry
     */
    public static void main(String args[]) {
        Model model = new Model();
        View view = new View(model,1600,1024,"Shooter2D");
        Controller controller = new Controller(view, model);
    }
}
