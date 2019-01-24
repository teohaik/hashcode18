
package hashCode18;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Thodoris
 */
public class Vehicle {
    
    int code;
    
    Set<Ride> rides = new HashSet();
    
    Ride assignedRide = null;
    
    int stepsToGoForThisRide;
       
    Point position;
    
   Integer stepsToReachStart;
   boolean enRoute;
   
    
    public Vehicle(int code){
        this.code = code;
        position = new Point(0,0);
        stepsToReachStart = null;
        enRoute = false;
    }
    
    public void assignRide(Ride r){
        assignedRide = r;
        rides.add(r);
        stepsToGoForThisRide = position.getDistanceFrom(r.finish);
    }

    int steps = 0;
       
    boolean wait = true;
   
    
    public void move(int step){
        if(assignedRide != null){
            
            if(stepsToReachStart == null && position.getDistanceFrom(assignedRide.start) > 0){
                stepsToReachStart = position.getDistanceFrom(assignedRide.start);
                stepsToReachStart = stepsToReachStart - 1 ;
            }
            else if (stepsToReachStart == 0){
                stepsToReachStart = null;
                enRoute = true;
            }
            else{
                stepsToReachStart = stepsToReachStart - 1 ;
            }
            
            if(step >= assignedRide.earliestStart && enRoute){
                steps++;
                stepsToGoForThisRide--;
               //System.out.println("Vehicle "+code+" moving  to next step");
                if(stepsToGoForThisRide == 0){
                    position = assignedRide.finish;
                    System.out.println("Vehicle "+code+" Arrived at "+assignedRide.finish.toString());
                    System.out.println("====================================================================================================== step "+step);
                    assignedRide = null;
                    enRoute = false;
                }
            }
        }
    }
    
}
