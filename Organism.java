import java.awt.Image;

public interface Organism {
    public Image getImage();
    public void step(int row, int col, Grid grid);
}
