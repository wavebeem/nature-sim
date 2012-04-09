import java.awt.Image;

public class Carrot extends Grass {
    private static final int maxAmount = 100;
    private static final int maxStepsUntilEdible = 50;
    
    private boolean alive;
    private int amount;
    private int stepsUntilEdible;
    
    public Carrot(){
        Debug.echo("Constructing a new Carrot object");
        
        alive = true;
        amount = maxAmount;
        stepsUntilEdible = 0;
    }
    public int getEaten(int amount){
        if(this.amount < amount){
            int eatenAmount = this.amount;
            
            this.amount = 0;
            alive = false;
            stepsUntilEdible = maxStepsUntilEdible;
            return eatenAmount;
        } else {
            this.amount -= amount;
            return amount;
        }
    }
    public void step(int row, int col, Grid grid){
        Debug.echo("Here is where the Carrot would step");
    }
    public Image getImage(){
        return null;
    }
}