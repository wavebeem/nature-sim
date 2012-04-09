import java.awt.Image;

public abstract class Plant implements Organism {
    protected boolean alive;
    protected int amount;
    protected int stepsUntilEdible;

    protected abstract int getMaxAmount();
    protected abstract int getMaxStepsUntilEdible();
    public abstract void step(int row, int col, Grid grid);
    public abstract Image getImage();
    
    public int getEaten(int amount){
        if(this.amount < amount){
            int eatenAmount = this.amount;
            
            this.amount = 0;
            alive = false;
            stepsUntilEdible = getMaxStepsUntilEdible();
            return eatenAmount;
        } else {
            this.amount -= amount;
            return amount;
        }
    }
}