package CompSciFinalProject.src;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.util.ArrayList;

public class Window {

    public final JFrame window;

    public JTextField text;

    public JTextField leftDomainTxt;
    public JTextField rightDomainTxt;

    public JPanel panel;

    public JPanel p;

    public ATJPanel graphs1;
    public JLabel fx;

    public int scaler = 1;

    public int leftDomain = -1000;
    public int rightDomain = 1000;



    public ArrayList<FunctionComponent> functions;
    public GraphComponent graphs;

    public JPanel main_panel;


    private JButton graph_all;

    public DomainRange dr;


    public Window() {
        JPanel domain_range = new JPanel();


        window = new JFrame();
        main_panel = new JPanel();
        functions = new ArrayList<>();
        graph_all = new JButton("GRAPH ALL");
        dr = new DomainRange(this, main_panel, "");

        window.setSize(550, 700);
        main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.PAGE_AXIS));
        graph_all.addActionListener(e -> {ButtonMethods.graph_all(this);});
        //main_panel.add(graph_all);

        functions.add(new FunctionComponent(this, main_panel, "f(x)"));
        functions.add(new FunctionComponent(this, main_panel, "g(x)"));
        functions.add(new FunctionComponent(this, main_panel, "z(x)"));
        functions.add(new FunctionComponent(this, main_panel, "t(x)"));

        graphs = new GraphComponent(this, main_panel);

//      ------------ REQUIRED CODE BELOW : DO NOT DELETE OR CHANGE ------------

        main_panel.add(graphs);
        window.add(main_panel);


        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        window.setVisible(true);




    }

    public void settle() {
        window.setSize(701, 700);
        window.setSize(700, 700);
    }


    //    public Window(boolean f) {
//        window = new JFrame();
//
//        panel = new JPanel();
//
//        JLabel label = new JLabel("f(x)");
//
//        fx = new JLabel();
//
//        JButton b = new JButton("GRAPH FUNCTION");
//
//
//        ButtonActionListener listener = new ButtonActionListener(this, 0);
//
//        window.setTitle("Calculator");
//
//        text = new JTextField(30);
//
//        leftDomainTxt = new JTextField("-1000", 10);
//        rightDomainTxt = new JTextField("1000",10);
//
//        leftDomainTxt.setSize(100,10);
//        rightDomainTxt.setSize(100,10);
//
//        text.setSize(100,10);
//
//        b.addActionListener(listener);
//
//        panel.add(label);
//
//        panel.add(text);
//
//        panel.add(b);
//
//        panel.add(fx);
//
//        window.setSize(700, 700);
//
//        graphs.setBounds(new Rectangle(70,50,500,400));
//
//        graphs.setBorder(BorderFactory. createLineBorder(Color. black));
//
//        Scrollbar scr = new Scrollbar();
//        ScrollBarListener b1 = new ScrollBarListener(this);
//        scr.addMouseListener(b1);
//
//        scr.setMinimum(1);
//        scr.setMaximum(1000);
//        p = new JPanel();
//        p.add(scr);
//        p.setBounds(new Rectangle(600, 70, 150,500));
//        p.add(leftDomainTxt);
//        p.add(rightDomainTxt);
//
//        scr.getValue();
//
//        window.add(graphs);
//
//        window.add(p);
//
//        window.add(panel);
//
//        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        window.setVisible(true);
//    }



}
