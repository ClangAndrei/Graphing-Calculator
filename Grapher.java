package CompSciFinalProject.src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Grapher extends JPanel {

    public Window win;
    public ArrayList<ArrayList<Point>> list_of_pts;
    public ArrayList<Point>[] graph_pts;
    public Color[] colors;

    public HashMap<Integer,ArrayList<Point>> list_of_functions;
    public int to_graph;

    public Grapher(GraphComponent g1, Window w) {

        super();
        win = w;
        graph_pts = new ArrayList[6];
        for(int i = 0; i < 6; i++) {
            graph_pts[i] = new ArrayList<>();
        }
        list_of_functions = new HashMap<>();
        list_of_pts = new ArrayList<>();
        colors = new Color[6];
        colors[0] = Color.RED;
        colors[1] = Color.BLUE;
        colors[2] = new Color(0,200,100); // the original green color is disgusting, so i am making a custom color
        colors[3] = Color.CYAN;
        colors[4] = Color.ORANGE;
        colors[5] = Color.BLACK;

    }

    @Override
    public void paintComponent(Graphics _g) {

        Graphics2D g = (Graphics2D)_g;

        g.setColor(Color.black);
        g.drawLine(250,0,250,400);
        g.drawLine(0,200,500,200);

        g.setStroke(new BasicStroke(2));

        int temp = 0;

        for(ArrayList<Point> p : graph_pts) {
            g.setColor(colors[temp]);
            for(int i = 0; i + 1 < p.size(); i+=1) {
                if(p.get(i).graph == false || p.get(i+1).graph == false) {
                    i+=1;
                    draw_asymptote(g,p.get(i).x);
                    g.setColor(colors[temp]);
                    continue;
                }
                g.drawLine(p.get(i).x + 250, -p.get(i).y + 200 , p.get(i+1).x + 250, -p.get(i+1).y + 200);
            }

            temp++;
        }

    }


    private void draw_asymptote(Graphics2D g, int x) {
        g.setStroke(new BasicStroke(1));
        for(int i = 0; i <= 400; i+=10) {
            g.drawLine(x + 250,i,x + 250,i+5);
        }
        g.setStroke(new BasicStroke(2));
    }


}
