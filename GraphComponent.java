package CompSciFinalProject.src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphComponent extends JPanel {
    private static Window window;
    private static JPanel main_panel;
    public static Grapher viewable;



    private JLabel label = new JLabel("Graphs");


    public GraphComponent(Window win, JPanel panel) {
        super();
        viewable = new Grapher(this, window);

        window = win;
        main_panel = panel;

        this.add(viewable);

        display();
    }

    public void display() {
        this.add(label);
    }


    @Override
    public void paintComponent(Graphics g) {
        viewable.setBounds((window.window.getWidth() / 2) - 250,main_panel.getHeight() - 600, 500,400);
        viewable.setBorder(BorderFactory.createLineBorder(Color.black));

    }


}
