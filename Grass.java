import java.awt.Image;

public class Grass extends Plant {
    private static final int maxAmount = 100;
    private static final int maxStepsUntilEdible = 50;
    
    public Grass(){
        Debug.echo("Constructing a new Grass object");
        
        alive = true;
        amount = maxAmount;
        stepsUntilEdible = 0;
    }
    protected int getMaxAmount(){
        return maxAmount;
    }
    protected int getMaxStepsUntilEdible(){
        return maxStepsUntilEdible;
    }
    public void step(int row, int col, Grid grid){
        Debug.echo("Here is where the Grass would step");
    }
    public Image getImage(){
        return null;
    }
}