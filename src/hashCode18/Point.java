package hashCode18;

/**
 *
 * @author Thodoris
 */
public class Point {
    
    
    int x,y;
    
    public Point(int ax, int ay){
        x=ax;
        y = ay;
    }
    
    
    public int getDistanceFrom(Point o){
        return Math.abs(x -  o.x) + Math.abs(y - o.y);
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
    
    
    
}
