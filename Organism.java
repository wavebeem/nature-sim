import java.awt.Image;

public abstract class Organism {
    private Location location;

    protected void setLocation(Location newLocation){
        location = newLocation;
    }
    public Location getLocation(){
        return location;
    }

    public abstract double getCalories();
    public abstract Image getImage();
    public abstract void step(Grid grid);
}
