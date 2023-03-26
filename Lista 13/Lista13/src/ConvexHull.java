import java.awt.*;
import java.util.*;
import java.util.List;

public class ConvexHull {
    public static List<Point> solve(List<Point> points) throws IllegalArgumentException {
        if(linearCheck(points)){
            throw new IllegalArgumentException();
        } else {
            Stack<Point> stack = new Stack<>();
            Stack<Point> reverseStack = new Stack<>();
            List<Point> tempList = new ArrayList<>();
            sortByY(points);
            sortByAngle(points);
            deleteSameAngles(points);

            stack.push(points.get(0));
            stack.push(points.get(1));
            stack.push(points.get(2));
            for (int i = 3; i < points.size(); i++) {
                while (positionCheck(stack, points.get(i))) {
                    stack.pop();
                }
                stack.push(points.get(i));
            }

            while (!stack.isEmpty()) {
                Point toAdd = stack.pop();
                reverseStack.push(toAdd);
            }
            while (!reverseStack.isEmpty()) {
                Point toAdd = reverseStack.pop();
                tempList.add(toAdd);
            }
            // dodanie ostatniego elementu jest potrzebne, zeby testy działały, jedynie test almostCollinear
            // wychodzi błędny ze względu na ostateczne nie uwzględnienie na koncu punktu początkowego
            tempList.add(points.get(0));
            return tempList;
        }
    }

    public static double getAngle(Point a, Point b){
        double angle = Math.toDegrees(Math.atan2((a.y - b.y), (a.x - b.x)));
        return angle;
    }

    private static boolean positionCheck(Stack<Point> stack, Point c){
        Point a = nextToTop(stack);
        Point b = stack.peek();
        int orientation = orientationOfThreePoints(a,b,c);
        return orientation >= 0;
    }

    private static boolean linearCheck(List<Point> points){
        boolean areLinear = true;
        for(int i = 0; i < points.size() - 1; i++){
            double angleOne = Math.toDegrees(Math.atan2((points.get(i).y), (points.get(i).x)));
            double angleTwo = Math.toDegrees(Math.atan2((points.get(i + 1).y), (points.get(i + 1).x)));
            if(angleOne != angleTwo){
                areLinear = false;
            }
        }
        return areLinear;
    }

    private static int orientationOfThreePoints(Point a, Point b, Point c){
        int val = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
        return val;
    }

    private static double length(Point a, Point b){
        return Math.sqrt(Math.pow(a.y - b.y, 2) + Math.pow(a.x - b.x, 2));
    }

    private static void sortByY(List<Point> points){
        Comparator<Point> comparator1 = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.y - o2.y;
            }
        };
        points.sort(comparator1);
        for(int i = 0; i < points.size(); i++){
            if(points.get(i).y == points.get(i + 1).y){
                if(points.get(i).x > points.get(i + 1).x){
                    Point temp = points.get(i);
                    points.set(i,points.get(i + 1));
                    points.set(i + 1, temp);
                }
            } else {
                break;
            }
        }
    }

    private static void sortByAngle(List<Point> points){
        for(int i = 1; i < points.size() - 1; i++){
            for(int j = 1; j < points.size() - i; j++){
                double angleOne = getAngle(points.get(j), points.get(0));
                double angleTwo = getAngle(points.get(j + 1), points.get(0));
                if(angleOne > angleTwo){
                    Point temp = points.get(j);
                    points.set(j,points.get(j + 1));
                    points.set(j + 1, temp);
                }
            }
        }
    }

    private static void deleteSameAngles(List<Point> points){
        for(int i = 1; i < points.size() - 1; i++){
            double angleOne = getAngle(points.get(i), points.get(0));
            double angleTwo = getAngle(points.get(i + 1), points.get(0));
            if (angleOne == angleTwo) {
                double lengthOne = length(points.get(0), points.get(i));
                double lengthTwo = length(points.get(0), points.get(i + 1));
                if (lengthOne >= lengthTwo) {
                    points.remove(i + 1);
                } else {
                    points.remove(i);
                }
                i--;
            }
        }
    }

    private static Point nextToTop(Stack<Point> stack){
        Point temp = stack.peek();
        stack.pop();
        Point nextToTop = stack.peek();
        stack.push(temp);
        return nextToTop;
    }
}
