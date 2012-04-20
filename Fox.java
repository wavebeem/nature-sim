import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.util.Collections;

public class Fox extends Animal {
    private static double calories;

    private static final int sightDistance = 6;
    private static final int moveDistance = 2;
    private static double maxHunger;
    private static final int maxAge = 100;
    
    private static ArrayList<String> prey = new ArrayList<String>();
    private static ArrayList<String> predators = new ArrayList<String>();
    
    public Fox(Location loc){
        Debug.echo("Constructing a new Fox object");
        setLocation(loc);
        hunger = 0;
        age = 0;
    }

    public void act(Grid grid){
        Debug.echo("Here is where the Fox would act");
        
        GridSquare mySquare = grid.get(getLocation());
        List<DistanceSquarePair> visibleSquares = grid.getAdjacentSquares(getLocation(), sightDistance);
        List<DistanceSquarePair> predatorSquares = grid.getOrganismSquares(visibleSquares, predators);
        
        if(predatorSquares.size() > 0) {
            Debug.echo("OH SHIT RUN?");
        }
        
        Organism bestAdjacentPrey = bestPreyInDistance(grid, prey, moveDistance);
        Organism bestVisiblePrey = bestPreyInDistance(grid, prey, sightDistance);
        
        if(bestAdjacentPrey != null) {
            eat(bestAdjacentPrey, grid);
            return;
        } else {
            //Move toward bestVisiblePrey?
        }
        
        //No prey in sight. Wander?
        List<DistanceSquarePair> emptyReachableSquares = grid.getEmptySquares(getLocation(), moveDistance);
        if (emptyReachableSquares.size() > 0) {
            move(grid, emptyReachableSquares.get(Util.randInt(emptyReachableSquares.size())).gridSquare);
        }
    }

    public static void addPrey(String p)     { prey.add(p);      }
    public static void addPredator(String p) { predators.add(p); }
    public static void setCalories(double c)    { 
        calories = c;     
        maxHunger = c * 50;
    }

    protected double getMaxHunger()    { return maxHunger;                     }
    protected int getMaxAge()       { return maxAge;                        }
    protected int getSightDistance(){ return sightDistance;                 }
    protected int getMoveDistance() { return moveDistance;                  }
    
    public double getCalories()        { return calories;                      }
    public String toString()        { return "Fox";                         }
    public Image getImage()         { return Resources.imageByName("Fox");  }
}
