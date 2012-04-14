import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Image;

public class Rabbit extends Animal {
    private static final int sightDistance = 10;
    private static final int moveDistance = 2;
    private static final int maxHunger = 100;
    private static final int maxAge = 100;
    
    private int stepNumber = 0;
    
    private static ArrayList<String> prey = new ArrayList<String>();
    private static ArrayList<String> predators = new ArrayList<String>();
    
    public Rabbit(){
        Debug.echo("Constructing a new Rabbit object");
        
        hunger = 0;
        age = 0;
    }

    public void act(Location loc, Grid grid){
        stepNumber++;
        System.out.println("Rabbit at loc="+loc+" is making its "+stepNumber+" step");
    
        GridSquare mySquare = grid.get(loc);
        List<DistanceSquarePair> visibleSquares = grid.getAdjacentSquares(loc, sightDistance);
        List<DistanceSquarePair> predatorSquares = grid.getOrganismSquares(visibleSquares, predators);
        List<DistanceSquarePair> preySquares = grid.getOrganismSquares(visibleSquares, prey);
        List<DistanceSquarePair> emptySquares = grid.getEmptySquares(visibleSquares);
        
        Plant myPlant = mySquare.getPlant();
        if(predatorSquares.size() > 0) {
            Debug.echo("OH SHIT RUN?");
        }
        if (myPlant != null && myPlant.isAlive()){
            myPlant.getEaten(10);
            eat(10);
            return;
        } else {
            List<DistanceSquarePair> reachableSquares = grid.getAdjacentSquares(loc, moveDistance);
            for(DistanceSquarePair pair: reachableSquares){
                if(emptySquares.contains(pair) && preySquares.contains(pair) && pair.gridSquare.getPlant().isAlive()){
                    grid.removeAnimal(loc);
                    pair.gridSquare.setAnimal(this);
                    
                    mySquare = grid.get(loc);
                    myPlant = mySquare.getPlant();
                    myPlant.getEaten(10);
                    eat(10);
                    return;
                }
            }
            List<DistanceSquarePair> emptyReachableSquares = emptySquares;
            emptyReachableSquares.retainAll(reachableSquares);
            grid.removeAnimal(loc);
            Random random = new Random();
            emptyReachableSquares.get(random.nextInt(emptyReachableSquares.size())).gridSquare.setAnimal(this);
        }
        
        Debug.echo("Here is where the Rabbit would act");
    }

    public Image getImage(){
        return Resources.imageByName("Rabbit");
    }

    public static void addPrey(String p)     { prey.add(p);      }
    public static void addPredator(String p) { predators.add(p); }

    protected int getMaxHunger(){
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
    
    public String toString(){
        return "Rabbit";
    }
}
