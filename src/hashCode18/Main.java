package hashCode18;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author teohaik
 */
public class Main {

    static ArrayList<Ride> rides  = new ArrayList();
    
    static final ArrayList<Vehicle> vehicles  = new ArrayList();
          
    public static void main(String[] args) throws FileNotFoundException {
        //String f1 = "a_example";
        String f1 = "b_should_be_easy";
        //String f1 = "c_no_hurry";
        //String f1 = "d_metropolis";
        //String f1 = "e_high_bonus";
        
        FileReader fr = new FileReader(new File("files\\"+f1+".in"));

        Scanner s = new Scanner(fr);
        
        StringTokenizer firstLineTokens = new StringTokenizer(s.nextLine());
        
        int rows = Integer.parseInt(firstLineTokens.nextToken());
        int cols = Integer.parseInt(firstLineTokens.nextToken());
        int VEHICLES = Integer.parseInt(firstLineTokens.nextToken());
        int RIDES = Integer.parseInt(firstLineTokens.nextToken());
        int BONUS = Integer.parseInt(firstLineTokens.nextToken());
        int STEPS = Integer.parseInt(firstLineTokens.nextToken());
        System.out.println(rows+ " "+cols+" "+VEHICLES+" "+RIDES+ " "+BONUS+" "+STEPS);
        
        for(int v=0; v<VEHICLES; v++){
            vehicles.add(new Vehicle(v));
        }
        
        readFile(RIDES, s);
        
        Collections.sort(rides, new RideEarliest());
        
        rides.stream()
                .sorted( 
                        Comparator.comparing((Ride r) -> r.earliestStart)
                );
                
        for(Ride ride : rides){
            System.out.println(ride);
        }

       int currentRide = 0;
      for(int step=0; step < STEPS; step ++){         
          
          while(getFreeVehicle() != null && currentRide < RIDES ) {
              System.out.println("Assining ride to vehicle at steps "+step);
            Ride r = rides.get(currentRide);
            Vehicle v = getNextFreeNearestVehicle(r);      
            currentRide++;
            v.assignRide(r);
          }
          
          
          for(Vehicle v: vehicles){
              v.move(step);
          }
  
      }      
        print(f1); 
    }

    static Vehicle getNextFreeVehicle(Ride r){
        try{
            Vehicle get = vehicles.stream().filter(v -> v.assignedRide == null)
                    
                    .min((v1, v2)-> 
                            Integer.compare(v1.position.getDistanceFrom(r.start),
                            v2.position.getDistanceFrom(r.start)))
                    .get();
            return get;
        }
        catch (java.util.NoSuchElementException ex) {
            return null;
        }
    }
    
    static Vehicle getNextFreeNearestVehicle(Ride r){
        try{
            Vehicle get = vehicles.stream().filter(v -> v.assignedRide == null)
                    
                    .min((v1, v2)-> 
                            Integer.compare(v1.position.getDistanceFrom(r.start),
                            v2.position.getDistanceFrom(r.start)))
                    .get();
            return get;
        }
        catch (java.util.NoSuchElementException ex) {
            return null;
        }
    }
    
    static Vehicle getFreeVehicle(){
        try{
            Vehicle get = vehicles.stream().filter(v -> v.assignedRide == null).findAny().get();
            return get;
        }
        catch (java.util.NoSuchElementException ex) {
            return null;
        }
    }
    
    static class RideEarliest implements Comparator<Ride> {

        @Override
        public int compare(Ride o1, Ride o2) {
            if(o1.earliestStart < o2.earliestStart){
                return -1;
            }
            return 1;
        }
        
    }
    
    
    private static void print(String f1) throws UnsupportedOperationException {
        StringBuilder sb = new StringBuilder();
        for(Vehicle v : vehicles){
            sb.append(v.rides.size()).append(" ");
            for(Ride r : v.rides){
                sb.append(r.code).append(" ");
            }
            sb.append("\n");
        }
        Path filePath = Paths.get("files\\"+f1+".out");
        File reportRouteFile = filePath.toFile();
        
        //τεχνική try with resources (Java 8) η οποία κλείνει αυτόματα 
        //τα resources μόλις τελειώσει η μέθοδος
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportRouteFile))){
            writer.write(sb.toString());
            System.out.println("Δημιουργήθηκε το αρχείο καταγραφής ");
        } catch (UnsupportedOperationException uoe) {
            System.err.println("Cannot write to file, exiting...");
            throw uoe;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static void readFile(int RIDES, Scanner s) throws NumberFormatException {

        for(int i=0; i<RIDES; i++){
            StringTokenizer lineTokens = new StringTokenizer(s.nextLine());
            int rowStart = Integer.parseInt(lineTokens.nextToken());
            int colStart = Integer.parseInt(lineTokens.nextToken());
            int rowEnd = Integer.parseInt(lineTokens.nextToken());
            int colEnd = Integer.parseInt(lineTokens.nextToken());
            int earliestStart = Integer.parseInt(lineTokens.nextToken()); 
            int latestFinish = Integer.parseInt(lineTokens.nextToken());
            
            Ride r = new Ride(i, rowStart, rowEnd, colStart, colEnd, earliestStart, latestFinish);
            rides.add(r);

        }
                 
        s.close();
    }
    
    
    
    
    
    
    
    
    
}
