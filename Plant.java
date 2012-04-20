import java.awt.Image;

public abstract class Plant extends Organism {
    protected boolean alive;
    protected int amount;
    protected int stepsUntilEdible;

    protected abstract int getMaxAmount();
    protected abstract int getMaxStepsUntilEdible();
    public abstract double getCalories();
    public abstract Image getImage();
    
    public void getEaten(){
        amount--;
        if(amount <= 0){
            amount = 0;
            alive = false;
            stepsUntilEdible = getMaxStepsUntilEdible();
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