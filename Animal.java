import java.awt.Image;

public abstract class Animal implements Organism {
    protected int age;
    protected int hunger;
    
    protected abstract int getMaxHunger();
    protected abstract int getMaxAge();
    protected abstract int getSightDistance();
    protected abstract int getMoveDistance();
    public abstract void act(int row, int col, Grid grid);
    public abstract Image getImage();
    
    public void step(int row, int col, Grid grid){
        age++;
        if(isOld()) {
            grid.removeAnimal(row, col);
        } else {
            act(row, col, grid);
        }
    }
    public boolean isOld(){
        return age >= getMaxAge();
    }
}
