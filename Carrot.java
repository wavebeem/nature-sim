import java.awt.Image;

public class Carrot extends Plant {
    private static int calories;

    private static int maxAmount;
    private static final int maxStepsUntilEdible = 50;
    
    public Carrot(Location loc){
        Debug.echo("Constructing a new Carrot object");
        setLocation(loc);
        alive = true;
        amount = maxAmount;
        stepsUntilEdible = 0;
    }

    
    public static void setCalories(int c)  { 
        calories = c;
        maxAmount = calories*5;
    }
    
    protected int getMaxAmount()           { return maxAmount;           }
    protected int getMaxStepsUntilEdible() { return maxStepsUntilEdible; }
    public int getCalories()               { return calories;            }
    public String toString() { return "Carrot"; }
    public Image getImage(){
        if(isAlive()){
            return Resources.imageByName("Carrot");
        } else {
            return null;
        }
    }
}
