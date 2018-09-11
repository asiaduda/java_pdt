package point;

public class Main {

  public static void main(String[] args){

    Point a = new Point(0, 0);
    Point b = new Point(3, 4);
    Point c = new Point(1, -1);

    System.out.println("Distance beetwen (" + a.x +" , " + a.y + ") and (" + b.x +" , " + b.y + ") is: " + a.distance(b));
    System.out.println("Distance beetwen (" + a.x +" , " + a.y + ") and (" + c.x +" , " + c.y + ") is: " + a.distance(c));

  }
}
