package CompSciFinalProject.src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Function {
    public ArrayList<String> function_to_graph;

    private ArrayList<String> numFunc;
    private static final String OPERATORS = "*/+-";
    public Function(ArrayList<String> input) {
        function_to_graph = shunting_yard(input);
        numFunc = new ArrayList<>();
    }

    public Function(){
        function_to_graph = new ArrayList<>();
        numFunc = new ArrayList<>();
    }

    public void graph(Window win, int value_to_graph, int d, int r) {
        function_to_graph = shunting_yard(function_to_graph);
        //System.out.println(function_to_graph);
        ArrayList<Point> p = return_points(d,r);
        win.graphs.viewable.graph_pts[value_to_graph] = p;
    }

    private void replace_x(Integer replace_with) {
        for(int i = 0; i < function_to_graph.size(); ++i) {
            if(function_to_graph.get(i).equals("x")) {
                numFunc.set(i, replace_with.toString());
            }
            if(function_to_graph.get(i).contains("sin")) {
                try {
                    numFunc.set(i, Math.sin(replace_string(function_to_graph.get(i).substring(4, function_to_graph.get(i).length()-1), replace_with)) + "");
                }
                catch (ArithmeticException e) {
                    numFunc.set(i, "de_rail");
                }
            }
            if(function_to_graph.get(i).contains("cos")) {
                try {
                    numFunc.set(i, Math.cos(replace_string(function_to_graph.get(i).substring(4, function_to_graph.get(i).length()-1), replace_with)) + "");
                }
                catch (ArithmeticException e) {
                    numFunc.set(i, "de_rail");
                }            }
            if(function_to_graph.get(i).contains("tan")) {
                try {
                    numFunc.set(i, Math.tan(replace_string(function_to_graph.get(i).substring(4, function_to_graph.get(i).length()-1), replace_with)) + "");
                }
                catch (ArithmeticException e) {
                    numFunc.set(i, "de_rail");
                }            }
            if(function_to_graph.get(i).contains("x^")) {
                numFunc.set(i, Math.pow(replace_with, Double.parseDouble(function_to_graph.get(i).substring(2))) + "");
            }

        }
    }

    private double replace_string(String str, int to_replace) {
        String newStr = "";

        ArrayList<String> fun_in = new ArrayList<>();
        for(int i = 0; i < str.length(); ++i) {

            if(str.charAt(i)=='x') {
                fun_in.add(to_replace + "");
                continue;
            }

            if(OPERATORS.contains(str.substring(i,i+1))) {
                fun_in.add(newStr);
                newStr = "";
                fun_in.add(str.substring(i,i+1));
                continue;
            }
            newStr += str.charAt(i);
        }

        fun_in.add(newStr);

        fun_in = shunting_yard(fun_in);
        try {
            double d = eval(fun_in);
            return d;
        }
        catch (ArithmeticException e) {
            throw new ArithmeticException();
        }
    }


    public ArrayList<Point> return_points(int d, int r) {
        ArrayList<Point> pts = new ArrayList<>();
        numFunc.clear();
        numFunc.addAll(function_to_graph);
        int t = 0;
        for(int i = -500; i <= 500; ++i) {
            replace_x(i);
            try {

                pts.add(new Point(i * d, (int)(eval(numFunc) * r), true));
                t++;
            }
            catch (Exception e){
                t++;
                pts.add(new Point(i, 0, false));
            }
        }

        return pts;
    }


    public ArrayList<String> shunting_yard(ArrayList<String> input) {

        Stack<String> st_op = new Stack<>();
        Queue<String> st_eq = new LinkedList<>();


        for(int i = 0; i < input.size(); ++i) {
            if(OPERATORS.contains(input.get(i))) {
                while(!st_op.isEmpty() &&  OPERATORS.indexOf(st_op.peek()) < OPERATORS.indexOf(input.get(i))) {
                    st_eq.add(String.valueOf(st_op.pop()));
                }
                st_op.push(input.get(i));
            }
            else {
                st_eq.add(input.get(i));
            }
        }

        ArrayList<String> hold = new ArrayList<>();

        while(!st_eq.isEmpty()) {
            hold.add(st_eq.remove());
        }
        while(!st_op.isEmpty()) {
            hold.add(st_op.pop());
        }

        return hold;
    }

    public void buildFromString(String in) {

        StringBuilder temp = new StringBuilder();

        for(int i = 0; i < in.length(); ++i) {

            if(in.charAt(i) == ' ') {
                function_to_graph.add(temp.toString());
                temp = new StringBuilder();
                continue;
            }
            temp.append(in.charAt(i));
        }

        function_to_graph.add(temp.toString());
    }




    public double eval(ArrayList<String> str) {

        Stack<String> stack = new Stack<>();
        String hold;
        for (int i = 0; i < str.size(); ++i) {
            if (!OPERATORS.contains(str.get(i))) {
                stack.push(str.get(i));
                continue;
            }
            if (str.get(i).equals("*")) {
                double holder = Double.parseDouble(stack.pop()) * Double.parseDouble(stack.pop());

                hold = "";
                hold += holder + "";
                stack.push(hold);
                continue;
            }
            if (str.get(i).equals("/")) {
                String a = stack.pop();
                String b = stack.pop();
                if(Double.parseDouble(a) == 0) {
                    throw new ArithmeticException();
                }

                double holder = Double.parseDouble(b) / Double.parseDouble(a);

                hold = "";
                hold += holder + "";
                stack.push(hold);
                continue;
            }
            if (str.get(i).equals("+")) {
                double holder = Double.parseDouble(stack.pop()) + Double.parseDouble(stack.pop());

                hold = "";
                hold += holder + "";
                stack.push(hold);
                continue;
            }
            if (str.get(i).equals("-")) {
                String a = stack.pop();
                String b = stack.pop();
                double holder = Double.parseDouble(b) - Double.parseDouble(a);

                hold = "";
                hold += holder + "";
                stack.push(hold);
            }
        }
        if(stack.isEmpty()) {
            throw new ArithmeticException();
        }
        return Double.parseDouble(stack.pop());
    }


    public String toString() {
        return function_to_graph.toString();
    }



    //    public double eval(){
//        return 0.0;
////
////        Stack<String> stack = new Stack<>();
////        String hold;
////        System.out.println(function_to_graph + " the function when in the eval method");
////        for(int i = 0; i < function_to_graph.size(); ++i) {
////            if(!operators.contains(function_to_graph.get(i))) {
////                stack.push(function_to_graph.get(i));
////                continue;
////            }
////            if(function_to_graph.get(i).equals(" ")) {
////                continue;
////            }
////            if(function_to_graph.get(i).equals("*")){
////                double holder = Double.parseDouble(stack.pop()) * Double.parseDouble(stack.pop());
////
////                hold = "";
////                hold += holder + "";
////                stack.push(hold);
////                continue;
////            }
////            if(function_to_graph.get(i).equals("/")){
////                String a = stack.pop();
////                String b = stack.pop();
////                double holder = Double.parseDouble(b) / Double.parseDouble(a);
////
////                hold = "";
////                hold += holder + "";
////                stack.push(hold);
////                continue;
////            }
////            if(function_to_graph.get(i).equals("+")){
////                double holder = Double.parseDouble(stack.pop()) + Double.parseDouble(stack.pop());
////
////                hold = "";
////                hold += holder + "";
////                stack.push(hold);
////                continue;
////            }
////            if(function_to_graph.get(i).equals("-")){
////                String a = stack.pop();
////                String b = stack.pop();
////                double holder = Double.parseDouble(b) - Double.parseDouble(a);
////
////                hold = "";
////                hold += holder + "";
////                stack.push(hold);
////            }
////        }
//        //return Double.parseDouble(stack.pop());
//    }


}
