import java.util.List;

public class Grid {
    private GridSquare[][]  gridSquares;
    private GridSummary[][] gridSummaries;

    public Grid(int gridSize) {
        Debug.echo("Making a grid from specified size");
        gridSquares = new GridSquare[gridSize][gridSize];
    }

    public Grid(String filename) {
        Debug.echo("Making a grid from specified filename");
        parseGrid(filename);
    }

    public void parseGrid(String filename) {
        Debug.echo("Parsing grid from file");
    }

    public GridSquare get(int row, int col) {
        return gridSquares[row][col];
    }

    public void removeAnimal(int row, int col) {
        Debug.echo("Removing animal at " + row + ", " + col); 
    }

    public List<GridSquare> getAdjacentLocations(int row, int col, int distance) {
        Debug.echo("Get adjacent locations");
    }

    public List<GridSquare> getEmptyLocations(int row, int col, int distance) {
        Debug.echo("Get empty locations");
    }

    public List<GridSquare> getOccupiedLocations(int row, int col, int distance) {
        Debug.echo("Get occupied locations");
    }

    public GridSquare[][]  getGridSquares()   { return gridSquares;   }
    public GridSummary[][] getGridSummaries() { return gridSummaries; }
}
