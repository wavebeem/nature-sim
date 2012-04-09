import java.awt.Image;

public class Carrot extends Plant {
    private static final int maxAmount = 100;
    private static final int maxStepsUntilEdible = 50;
    
    public Carrot(){
        Debug.echo("Constructing a new Carrot object");
        
        alive = true;
        amount = maxAmount;
        stepsUntilEdible = 0;
    }
    public void step(int row, int col, Grid grid){
        Debug.echo("Here is where the Carrot would step");
    }
    public Image getImage(){
        return null;
    }
    protected int getMaxAmount(){
        return maxAmount;
    }
    protected int getMaxStepsUntilEdible(){
        return maxStepsUntilEdible;
    }
}