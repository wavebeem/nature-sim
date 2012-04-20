import java.awt.Image;

public abstract class Plant extends Organism {
    protected boolean alive;
    protected int amount;
    protected int stepsUntilEdible;

    protected abstract int getMaxAmount();
    protected abstract int getMaxStepsUntilEdible();
    public abstract int getCalories();
    public abstract Image getImage();
    
    public int getEaten(){
        System.out.println("Eating "+getCalories()+" from plant with amount "+amount+" at loc "+getLocation()+".");
        if(amount < getCalories()){
            int eatenAmount = amount;
            
            amount = 0;
            alive = false;
            stepsUntilEdible = getMaxStepsUntilEdible();
            return eatenAmount;
        } else {
            this.amount -= getCalories();
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