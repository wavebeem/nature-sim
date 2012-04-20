import java.awt.Image;

public class Carrot extends Plant {
    private static int calories;

    private static final int maxAmount = 100;
    private static final int maxStepsUntilEdible = 50;
    
    public Carrot(Location loc){
        Debug.echo("Constructing a new Carrot object");
        setLocation(loc);
        alive = true;
        amount = maxAmount;
        stepsUntilEdible = 0;
    }

    public static int getCalories()        { return calories;            }
    public static void setCalories(int c)  { calories = c;               }
    
    protected int getMaxAmount()           { return maxAmount;           }
    protected int getMaxStepsUntilEdible() { return maxStepsUntilEdible; }
    
    public String toString() { return "Carrot"; }
    public Image getImage(){
        if(isAlive()){
            return Resources.imageByName("Carrot");
        } else {
            return null;
        }
    }

    
}
