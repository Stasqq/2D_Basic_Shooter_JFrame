package View;
import Controller.*;
import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Część projektu odpowiedzialna za wyświetlanie i kontakt z użytkownikiem
 */
public class View {
    private JFrame frame;
    Model model;

    public View(Model model,int width, int height, String title) {
        this.model=model;
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void addController(Controller controller) {
        frame.add(controller);
    }

    public void render(Controller controller){
        BufferStrategy bs=controller.getBufferStrategy();
        if(bs==null){
            controller.createBufferStrategy(3);
            return;
        }

        Graphics g=bs.getDrawGraphics();
        /////////////////////////////////////////////////////////////////

        g.setColor(Color.red);
        g.fillRect(0,0,1600,1024);

        model.getHandler().render(g);

        /////////////////////////////////////////////////////////////////
        g.dispose();
        bs.show();
    }
}
