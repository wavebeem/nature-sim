import java.util.ArrayList;
import java.awt.Image;

public class Rabbit extends Animal {
    private static final int sightDistance = 10;
    private static final int moveDistance = 2;
    private static final int maxHunger = 100;
    private static final int maxAge = 100;
    
    private static ArrayList<String> prey = new ArrayList<String>();
    private static ArrayList<String> predators = new ArrayList<String>();
    
    public Rabbit(){
        Debug.echo("Constructing a new Rabbit object");
        
        hunger = 0;
        age = 0;
    }

    public void act(int row, int col, Grid grid){
        Debug.echo("Here is where the Rabbit would act");
    }

    public Image getImage(){
        return null;
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
