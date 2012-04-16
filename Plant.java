import java.awt.Image;

public abstract class Plant extends Organism {
    protected boolean alive;
    protected int amount;
    protected int stepsUntilEdible;

    protected abstract int getMaxAmount();
    protected abstract int getMaxStepsUntilEdible();
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
    public void step(Grid grid){
        if(!alive){
            stepsUntilEdible--;
            if(stepsUntilEdible <= 0){
                amount = getMaxAmount();
                alive = true;
            }
        }
    }
    public boolean isAlive() {
        return alive;
    }
}