import java.awt.Image;

public interface Organism {
    public Image getImage();
    public void step(Location loc, Grid grid);
}
