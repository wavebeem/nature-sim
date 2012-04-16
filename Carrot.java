import java.awt.Image;

public class Carrot extends Plant {
    private static final int maxAmount = 100;
    private static final int maxStepsUntilEdible = 50;
    
    public Carrot(Location loc){
        Debug.echo("Constructing a new Carrot object");
        setLocation(loc);
        alive = true;
        amount = maxAmount;
        stepsUntilEdible = 0;
    }

    public Image getImage(){
        return Resources.imageByName("Carrot");
    }

    protected int getMaxAmount(){
        return maxAmount;
    }

    protected int getMaxStepsUntilEdible(){
        return maxStepsUntilEdible;
    }

    public String toString() {
        return "Carrot";
    }
}
