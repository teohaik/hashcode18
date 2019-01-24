/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashCode18;

import java.util.Comparator;

/**
 *
 * @author Thodoris
 */
public class Ride {
    
    int code;
    
    Point start, finish;
    
    int rowStart, rowEnd, colStart, colEnd, earliestStart, latestFinish;

    public Ride(int code, int rowStart, int rowEnd, int colStart, int colEnd, int earliestStart, int latestFinish) {
        this.code = code;
        start = new Point(rowStart, colStart);
        finish = new Point(rowEnd, colEnd);
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.colStart = colStart;
        this.colEnd = colEnd;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
    }
 
    
    

    @Override
    public String toString() {
        return "Ride CODE: |"+code+"|  start: ("+rowStart+","+colStart+") end: ("+rowEnd+","+colEnd+"). Earliest start = "+earliestStart+" LatFin : "+latestFinish;
    }

    @Override
    public int hashCode() {
        return rowStart+rowEnd+colStart+colEnd+earliestStart+latestFinish;
    }

    @Override
    public boolean equals(Object o) {
         if(o instanceof Ride){
             Ride or = (Ride)o;
             return this.hashCode() == or.hashCode();
         }
         return false;
    }
    
    
    
    
    
}
