import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.util.Collections;

public class Bear extends Animal {
    private static double calories;
    private static final int maxBreedingTime = 15; 
    private static final int sightDistance = 10;
    private static final int moveDistance = 3;
    private static double maxHunger;
    private static final int maxAge = 250;
    
    private static ArrayList<String> prey = new ArrayList<String>();
    private static ArrayList<String> predators = new ArrayList<String>();
    private static ArrayList<String> hidingSpots = new ArrayList<String>();
    private static ArrayList<String> competitors = new ArrayList<String>();
    
    public Bear(Location loc){
        init(loc);
    }

    public void addMyType(Grid grid, GridSquare square) {
        grid.addAnimal(new Bear(square.getLocation()), square);
    }

    public static void addPrey(String p) {
        if (predators.contains(p)) {
            predators.remove(p);
            competitors.add(p);
        } else {
            prey.add(p); 
        }  
    }
    public static void addPredator(String p) {
        if (prey.contains(p)) {
            prey.remove(p);
            competitors.add(p);
        } else {
            predators.add(p); 
        }  
    }
    public static void addHidingSpot(String p) { hidingSpots.add(p); }
    public static void addCompetitor(String p) { competitors.add(p); }
    public static void setCalories(double c)   { 
        calories = c;     
        maxHunger = c * 25;
    }

    public int getMaxBreedingTime() {
        return maxBreedingTime;
    }

    protected double getMaxHunger() { return maxHunger;                     }
    protected int getMaxAge()       { return maxAge;                        }
    protected int getSightDistance(){ return sightDistance;                 }
    protected int getMoveDistance() { return moveDistance;                  }
    
    protected ArrayList<String> getPredators()   { return predators;   }
    protected ArrayList<String> getPrey()        { return prey;        }
    protected ArrayList<String> getHidingSpots() { return hidingSpots; }
    protected ArrayList<String> getCompetitors() { return competitors; }
    
    public double getCalories()     { return calories;                      }
    public String toString()        { return "Bear";                        }
    public Image getImage()         { return Resources.imageByName("Bear"); }
}

