package CompSciFinalProject.src;

import java.io.File;
import java.util.ArrayList;

public class ButtonMethods {


    public static void graph_function(Window win, int value_to_graph, Function f) {

        if(win.functions.get(value_to_graph).input.getText().isEmpty() && f.function_to_graph.isEmpty()) {
            return;
        }
        f.function_to_graph.clear();
        win.graphs.viewable.graph_pts[value_to_graph].clear();

        String in = win.functions.get(value_to_graph).input.getText();

        f.buildFromString(in);



        
        int dHold;
        try {
            dHold = Integer.parseInt(win.dr.d.getText());
        }
        catch (NumberFormatException e) {
            dHold = 1;
        }
        int rHold;
        try {
            rHold = Integer.parseInt(win.dr.r.getText());
        }
        catch (NumberFormatException e) {
            rHold = 1;
        }

        f.graph(win, value_to_graph, dHold, rHold);

        win.settle();

    }


    public static void graph_all(Window win) {
        for(int i = 0; i < win.functions.size(); ++i) {
            graph_function(win, i, win.functions.get(i).function);

        }
    }
}
