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
        if(isOld()) {
            grid.removeAnimal(loc.row, loc.col);
        } else {
            act(loc, grid);
        }
    }
    public boolean isOld(){
        return age >= getMaxAge();
    }
}
