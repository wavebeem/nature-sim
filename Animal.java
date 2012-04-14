import java.awt.Image;

public abstract class Animal implements Organism {
    protected int age;
    protected int hunger;
    
    protected abstract int getMaxHunger();
    protected abstract int getMaxAge();
    protected abstract int getSightDistance();
    protected abstract int getMoveDistance();
    public abstract void act(Location loc, Grid grid);
    public abstract Image getImage();
    
    public void step(Location loc, Grid grid){
        age++;
        hunger+= 5;
        if(isOld() || isStarving()) {
            System.out.print("Animal at "+loc+" died due to ");
            if(isOld()){
                System.out.println("old age");
            } else {
                System.out.println("hunger");
            }
            grid.removeAnimal(loc);
        } else {
            act(loc, grid);
        }
    }
    public boolean isOld(){
        return age >= getMaxAge();
    }
    public boolean isStarving(){
        return hunger >= getMaxHunger();
    }
    protected void eat(int amount){
        hunger -= amount;
        if(hunger < 0) {
            hunger = 0;
        }
    }
}
