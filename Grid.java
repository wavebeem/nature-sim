import java.util.List;

public class Grid {
    private GridSquare[][]  gridSquares;
    private GridSummary[][] gridSummaries;

    public Grid(int gridSize) {
        Debug.echo("Making a grid from specified size");

        gridSquares = new GridSquare[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gridSquares[i][j] = new GridSquare();
            }
        }
    }

    public GridSquare get(int row, int col) {
        return gridSquares[row][col];
    }

    public void addAnimal(Animal animal, int row, int col) {
        gridSquares[row][col].setAnimal(animal);
    }

    public void addPlant(Plant plant, int row, int col) {
        gridSquares[row][col].setPlant(plant);
    }

    public void removeAnimal(int row, int col) {
        Debug.echo("Removing animal at " + row + ", " + col);
        gridSquares[row][col].setAnimal(null);
    }

    private boolean inBounds(int row, int col) {
        return row >= 0 && row < getGridSize()
            && col >= 0 && col < getGridSize();
    }

    public List<GridSquare> getAdjacentLocations(int row, int col, int distance) {
        Debug.echo("Get adjacent locations");
        return null;
    }

    public List<GridSquare> getEmptyLocations(int row, int col, int distance) {
        Debug.echo("Get empty locations");
        return null;
    }

    public List<GridSquare> getOccupiedLocations(int row, int col, int distance) {
        Debug.echo("Get occupied locations");
        return null;
    }

    public GridSquare[][]  getGridSquares()   { return gridSquares;   }
    public GridSummary[][] getGridSummaries() { return gridSummaries; }
    public int getGridSize() { return gridSquares.length; }
}
