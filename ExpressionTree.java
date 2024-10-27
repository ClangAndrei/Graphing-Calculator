package CompSciFinalProject.src;

import java.util.ArrayList;
import java.util.Stack;

/**
 * this java class has been heavily modified, with methods working differently, and in general, a completely
 * different implementation of the ExpressionTreeClass
 *
 * First : ExpressionTree class does not extend the TreeNode class anymore. instead, it has a field
 * root, which is a TreeNode. from there, the implementation of each method changes, as we can now
 * traverse the tree in an easier fashion. the reason for this change is beacause in my opinion, the
 * ExpressionTree has a bunch of TreeNodes inside it, and we can access them through one root.
 * it did not make sense to have ExpressionTrees pointing to themselves, and whilst there may be
 * benefits, (for example, an eval method might be easier to implement) it ultimatly complicates
 * the code to such a large extent that it doesnt make sense as an implementatioin.
 *
 * Second : the eval method, instead of destroying the Tree (the original design) now instead uses a stack
 * imilarly to the shunting yard algorithm. this helps preserve the tree for future use, if ened be.
 *
 * Third : The Tree can now be constructed from in-fix notation, which allows for better user integration.
 *
 * Fourth : the Tree supports all operands (except %) so *, /, +, and - all work on the tree, with good accuracy
 *
 * Fifth : the class ExpressionTree implements methods, such as toInFix and toPreFix that allow more access
 *  * for the user, with the tree itself.
 *  *
 *  * Sixth : as in any way, when a Tree is being cosntructed, it takes in the parameters String[] token,
 *  * we can not use multiple constructors, as they will have the same parameters, and therefore wont compile.
 *  * instead, the constructor relies on the User to tell it wether or not the parameter being given is
 *  * in in-fix, or in post-fix notation. this is done by the boolean infix we see in the constructor. in addition,
 *  * there is no no-args constructor, as you will never have a Tree of nothing.
 *  *
 *  * Seventh : a field called solution has been added, which stores the final value of the tree. this means
 *  * that the user does not have to run eval everytime, which runs in O(n). this runs in constant time. however,
 *  * an initial call to eval must be made.
 *
 *
 *   * Eight : a field called solved, which will report back if the tree is solved. this helps runtime complexity,
 *  * as if a tree has been solved before, there is no need to re-do all the calculations. users are expected
 *  * to use the getSolution method of the tree class, and not necesarily the eval method.
 *  */

public class ExpressionTree {

    private TreeNode root;

    private double solution = 0.0;
    private boolean solved = false;
    /**
     *
     * @param token the string array to convert into a tree
     * @param infix this contructore can take in either an expression as infix notation, or postfix
     *              put true for infix, and false for postfix
     */
    public ExpressionTree(String[] token, boolean infix){
        if (infix) {
            ArrayList<String> str = new ArrayList<>();
            for (String s : token) {
                str.add(s);
            }
            root = from_infix(str);
            return;
        }
        root = make_from_postfix(token);
    }

    public ExpressionTree(ArrayList<String> token){
        root = from_infix(token);
    }

    public TreeNode getRoot() {
        return root;
    }

    public double getSolution() {
        if(!solved) {
            eval();
        }
        return solution;
    }

    private TreeNode make_from_postfix(String[] token) {
        String operators = "*/+-";
        Stack<TreeNode> nodes_stack = new Stack<>();
        for (int i = 0; i < token.length; ++i) {
            if (!operators.contains(token[i])) {
                nodes_stack.push(new TreeNode(token[i]));
            } else {
                TreeNode hold = nodes_stack.pop();
                nodes_stack.push(new TreeNode(token[i], nodes_stack.pop(), hold));

            }
        }
        return nodes_stack.pop();
    }

    public double eval(){
        ArrayList<String> str = toListPostFixNotation();
        String operators = "*/+-";
        Stack<String> stack = new Stack<>();
        String hold;

        for(int i = 0; i < str.size(); ++i) {
            if(!operators.contains(str.get(i))) {
                stack.push(str.get(i));
                continue;
            }
            if(str.get(i).equals("*")){
                double holder = Double.parseDouble(stack.pop()) * Double.parseDouble(stack.pop());

                hold = "";
                hold += holder + "";
                stack.push(hold);
                //System.out.println(hold);
                continue;
            }
            if(str.get(i).equals("/")){
                String a = stack.pop();
                String b = stack.pop();
                double holder = Double.parseDouble(b) / Double.parseDouble(a);

                hold = "";
                hold += holder + "";
                stack.push(hold);
                //System.out.println(holder);
                continue;
            }
            if(str.get(i).equals("+")){
                double holder = Double.parseDouble(stack.pop()) + Double.parseDouble(stack.pop());

                hold = "";
                hold += holder + "";
                stack.push(hold);

                continue;
            }
            if(str.get(i).equals("-")){
                String a = stack.pop();
                String b = stack.pop();
                double holder = Double.parseDouble(b) - Double.parseDouble(a);

                hold = "";
                hold += holder + "";
                stack.push(hold);
                continue;
            }
            System.out.println(stack);
        }
        solved = true;
        solution = Double.parseDouble(stack.pop());
        return solution;
    }

    public static double eval(String[] token, boolean in_fix){
        ExpressionTree tree = new ExpressionTree(token, in_fix);
        return tree.getSolution();

    }

    public String toPostFixNotation() {
        return to_post(new StringBuilder(), root).toString();
    }

    private StringBuilder to_post(StringBuilder str, TreeNode root) {
        if(root!=null) {
            to_post(str, root.getLeft());
            to_post(str, root.getRight());
            str.append(root.getValue());
            return str;
        }
        return str;
    }

    private ArrayList<String> toListPostFixNotation() {
        ArrayList<String> expression = new ArrayList<>();
        to_post_list(expression, root);
        return expression;
    }

    private void to_post_list(ArrayList<String> str, TreeNode root) {
        if(root!=null) {
            to_post_list(str, root.getLeft());
            to_post_list(str, root.getRight());
            str.add(root.getValue().toString());
        }

    }

    public String toInFixNotation() {
        return to_infix(new StringBuilder(), root).toString();
    }

    private StringBuilder to_infix(StringBuilder str, TreeNode root) {
        if(root!=null) {
            to_infix(str, root.getLeft());
            str.append(root.getValue());
            to_infix(str, root.getRight());
            return str;
        }
        return str;
    }


    public String toPreFixNotation() {
        return to_pre(new StringBuilder(""),root).toString();
    }
    private StringBuilder to_pre(StringBuilder str, TreeNode root) {
        if(root!=null) {
            str.append(root.getValue());
            to_pre(str, root.getLeft());
            to_pre(str, root.getRight());

            return str;
        }
        return str;
    }


    private TreeNode from_infix(ArrayList<String> token) {
        if (token.size() <= 1) {
            return new TreeNode(token.get(0));
        }
        if (!token.contains("+")) {
            return makeTreeOfSub(token);
        }
        else {
            int mid = token.indexOf("+");
            ArrayList<String> left = new ArrayList<>();
            for(int i = 0; i < mid; i++) {
                left.add(token.get(i));
            }

            ArrayList<String> right = new ArrayList<>();
            for (int i = mid + 1; i < token.size(); i++) {
                right.add(token.get(i));
            }

            TreeNode leftN = from_infix(left);
            TreeNode rightN = from_infix(right);

            return new TreeNode(token.get(mid), leftN, rightN);
        }
    }

    private TreeNode makeTreeOfSub(ArrayList<String> token) {
        if (token.size() <= 1) {
            return new TreeNode(token.get(0));
        }
        if (!token.contains("-")) {
            return makeTreeOfMultiplication(token);
        }
        else {
            int mid = token.indexOf("-");
            ArrayList<String> left = new ArrayList<>();
            for(int i = 0; i < mid; i++) {
                left.add(token.get(i));
            }

            ArrayList<String> right = new ArrayList<>();
            for (int i = mid+1; i < token.size(); i++) {
                right.add(token.get(i));
            }

            return new TreeNode(token.get(mid), makeTreeOfSub(left), makeTreeOfSub(right));
        }
    }



    private TreeNode makeTreeOfMultiplication(ArrayList<String> token) {
        if (token.size() <= 1) {
            return new TreeNode(token.get(0));
        }

        if(!token.contains("*")) {
            return makeTreeOfDivision(token);
        }

        int mid = token.indexOf("*");

        ArrayList<String> left = new ArrayList<>();
        for(int i = 0; i < mid; i++) {
            left.add(token.get(i));
        }

        ArrayList<String> right = new ArrayList<>();
        for(int i = mid+1; i < token.size(); i++) {
            right.add(token.get(i));
        }

        return new TreeNode(token.get(mid), makeTreeOfMultiplication(left), makeTreeOfMultiplication(right));

    }

    private TreeNode makeTreeOfDivision(ArrayList<String> token) {
        if (token.size() <= 1) {
            return new TreeNode(token.get(0));
        }

        if(!token.contains("/")) {
            return null;
        }

        int mid = token.indexOf("/");

        ArrayList<String> left = new ArrayList<>();
        for(int i = 0; i < mid; i++) {
            left.add(token.get(i));
        }

        ArrayList<String> right = new ArrayList<>();
        for(int i = mid+1; i < token.size(); i++) {
            right.add(token.get(i));
        }

        return new TreeNode(token.get(mid), makeTreeOfDivision(left), makeTreeOfDivision(right));
    }


}
