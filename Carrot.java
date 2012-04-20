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

    protected int getMaxAmount()           { return maxAmount;           }
    protected int getMaxStepsUntilEdible() { return maxStepsUntilEdible; }
    public static int getCalories()        { return calories;            }

    public Image getImage(){
        if(isAlive()){
            return Resources.imageByName("Carrot");
        } else {
            return null;
        }
    }

    public static void setCalories(int c) {
        calories = c;
    }

    public String toString() {
        return "Carrot";
    }
}
