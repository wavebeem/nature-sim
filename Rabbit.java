import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.util.Collections;

public class Rabbit extends Animal {
    private static double calories;
    private static final int maxBreedingTime = 25; // max timesteps before it breeds
    private int breedingTime;

    private static double healthyHunger;

    private static final int sightDistance = 6;
    private static final int moveDistance = 2;
    private static double maxHunger;
    private static final int maxAge = 100;
    
    private static ArrayList<String> prey = new ArrayList<String>();
    private static ArrayList<String> predators = new ArrayList<String>();
    
    public Rabbit(Location loc){
        Debug.echo("Constructing a new Rabbit object");
        setLocation(loc);
        hunger = maxHunger * 0.75;
        age = 0;
        breedingTime = maxBreedingTime;
    }

    public void act(Grid grid){
        Debug.echo("Here is where the Rabbit would act");
        
        GridSquare mySquare = grid.get(getLocation());
        List<DistanceSquarePair> visibleSquares = grid.getAdjacentSquares(getLocation(), sightDistance);
        List<DistanceSquarePair> predatorSquares = grid.getOrganismSquares(visibleSquares, predators);
        List<DistanceSquarePair> preySquares = grid.getOrganismSquares(visibleSquares, prey);
        List<DistanceSquarePair> emptySquares = grid.getEmptySquares(visibleSquares);
        List<DistanceSquarePair> reachableSquares = grid.getAdjacentSquares(getLocation(), moveDistance);
        Collections.shuffle(reachableSquares);

        if (breedingTime > 0) breedingTime--;
        if (breedingTime == 0 && hunger <= healthyHunger) {
            breedingTime = maxBreedingTime;
            for (DistanceSquarePair pair : reachableSquares) {
                if (pair.gridSquare.getAnimal() == null) {
                    grid.addAnimal(new Rabbit(pair.gridSquare.getLocation()), pair.gridSquare);
                    break;
                }
            }
        }
        
        Plant myPlant = mySquare.getPlant();
        if(predatorSquares.size() > 0) {
            Debug.echo("OH SHIT RUN?");
        }
        if (myPlant != null && myPlant.isAlive() && prey.contains(myPlant.getClass().getName())){
            myPlant.getEaten();
            eat(myPlant.getCalories());
            return;
        } else {
            for(DistanceSquarePair pair: reachableSquares){
                if(emptySquares.contains(pair) && preySquares.contains(pair)){
                    move(grid, pair.gridSquare);
                    
                    mySquare = grid.get(getLocation());
                    myPlant = mySquare.getPlant();
                    myPlant.getEaten();
                    eat(myPlant.getCalories());
                    return;
                }
            }
            List<DistanceSquarePair> emptyReachableSquares = emptySquares;
            emptyReachableSquares.retainAll(reachableSquares);
            if (emptyReachableSquares.size() > 0) {
                move(grid, emptyReachableSquares.get(Util.randInt(emptyReachableSquares.size())).gridSquare);
            }
        }
    }

    public static void addPrey(String p)     { prey.add(p);      }
    public static void addPredator(String p) { predators.add(p); }
    public static void setCalories(double c)    { 
        calories = c;
        maxHunger = c * 50;
        healthyHunger = maxHunger / 2;
    }
    
    protected double getMaxHunger(){
        return maxHunger;
    }

    protected int getMaxAge(){
        return maxAge;
    }

    protected int getSightDistance(){
        return sightDistance;
    }

    protected int getMoveDistance(){
        return moveDistance;
    }
    
    public double getCalories()        { return calories;                          }
    public String toString()        { return "Rabbit";                          }
    public Image getImage()         { return Resources.imageByName("Rabbit");   }
}
