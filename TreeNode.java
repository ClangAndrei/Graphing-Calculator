package CompSciFinalProject.src;

public class TreeNode {
    private Object value;
    private TreeNode left;
    private TreeNode right;
    public TreeNode(Object init) {
        value = init;
    }

    public TreeNode(Object init , TreeNode left, TreeNode right){
        value = init;
        this.left = left;
        this.right = right;
    }
    public Object getValue() {
        return value;
    }
    public TreeNode getLeft() {
        return left;
    }
    public TreeNode getRight() {
        return right;
    }
    public void setValue(Object val) {
        value = val;
    }
    public void setLeft(TreeNode left) {
        this.left = left;
    }
    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return toStringBuilder(0).toString();
    }


    private StringBuilder toStringBuilder(int level) {

        StringBuilder s = new StringBuilder();
        //s.append("|");
        s.append("-".repeat(level));
        s.append(value);

        if(left != null){
            s.append("\n");
            s.append(left.toStringBuilder(level+1));
        }
        if(right != null){
            s.append("\n");
            s.append(right.toStringBuilder(level+1));
        }
        return s;
    }

    public String to_Str(StringBuilder str) {
        //str.append(getValue());
        if(left != null) {
            left.to_Str(str);
        }
        str.append(getValue());
        if(right != null) {
            right.to_Str(str);
        }
        return str.toString();
    }
}
