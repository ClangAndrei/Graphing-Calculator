package CompSciFinalProject.src;

class Point {
    public int x; public int y;
    public boolean graph;

    public Point(int x1, int y1, boolean t) {
        x = x1;
        y = y1;
        graph = t;
    }

    public String toString() {
        return x + " :: " + y;
    }

}