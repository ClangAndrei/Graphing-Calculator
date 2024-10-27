package CompSciFinalProject.src;

import javax.swing.*;
import java.awt.*;

public class FunctionComponent extends JPanel {
    private static int num_funcs = -1;
    private int my_num;
    private static Window window;
    private static JPanel main_panel;
    private JLabel label = new JLabel("f(x)");
    public JTextField input;
    public Function function;
    private JButton graph = new JButton("GRAPH FUNCTION");;


    public FunctionComponent(Window win, JPanel main, String txt) {
        window = win;
        main_panel = main;
        label.setText(txt);
        num_funcs++;
        my_num = num_funcs;
        function = new Function();
        display();
    }

    public void display() {
        this.add(label);

        input = new JTextField(30);

        input.setSize(100,10);

        this.add(input);

        // use of lambda is necessary, as the param is a ButtonAction Listener, and a Lambda
        // means i dont need a custom ButtonAction Listener class, so the code for buttons
        // can be neatly stored in the ButtonMethods class (more code re-use, write less code)
        graph.addActionListener(e -> {
            ButtonMethods.graph_function(window, my_num, function);
        });

        this.add(graph);

        this.setMaximumSize(new Dimension(600, 30));

        main_panel.add(this);

        main_panel.setBounds(0, 0, this.getWidth(), main_panel.getHeight() + 60);
        window.settle();
    }
}
