package CompSciFinalProject.src;

import javax.swing.*;
import java.awt.*;

public class DomainRange extends JPanel {
    private static int num_funcs = -1;
    private int my_num;
    private static Window window;
    private static JPanel main_panel;
    private JLabel label = new JLabel("f(x)");
    public JTextField input;
    public Function function;
    private JButton graph = new JButton("GRAPH FUNCTION");;

    public TextField d;
    public TextField r;


    public DomainRange(Window win, JPanel main, String txt) {
        window = win;
        main_panel = main;

        display();
    }

    public void display() {

        d = new TextField("1", 10);
        r = new TextField("1",10);
        d.setSize(40,10);
        r.setSize(40,10);
        this.add(new JLabel("X Scale"));
        this.add(d);
        this.add(new JLabel("Y Scale"));
        this.add(r);
        this.setMaximumSize(new Dimension(500, 30));
        JButton graph_all = new JButton("GRAPH ALL");
        graph_all.addActionListener(e -> {ButtonMethods.graph_all(window);});
        this.add(graph_all);
        main_panel.add(this);
        window.settle();

    }
}

