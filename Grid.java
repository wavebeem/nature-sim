import java.util.List;
import java.util.ArrayList;

public class Grid {
    private GridSquare[][]  gridSquares;
    private GridSummary[][] gridSummaries;
    private Location[] locations;

    public Grid(int gridSize) {
        Debug.echo("Making a grid from specified size");

        gridSquares = new GridSquare[gridSize][gridSize];
        locations = new Location[gridSize*gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gridSquares[i][j] = new GridSquare();
                locations[i*gridSize+j] = new Location(i, j);
            }
        }
    }

    public GridSquare get(int row, int col) {
        return gridSquares[row][col];
    }

    public GridSquare get(Location loc) {
        return gridSquares[loc.row][loc.col];
    }

    public void addAnimal(Animal animal, int row, int col) {
        if (animal == null) return;
        gridSquares[row][col].setAnimal(animal);
    }

    public void addPlant(Plant plant, int row, int col) {
        if (plant == null) return;
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

    private int distance(int x, int y, int i, int j) {
        return Math.abs(x - i) + Math.abs(y - j);
    }

    public List<GridSquare> getAdjacentGridSquares(Location loc, int dist) {
        Debug.echo("Get adjacent gridSquares");

        List<Location> locs = getAdjacentLocations(loc, dist);
        ArrayList<GridSquare> ret = new ArrayList();

        for (Location l : locs) {
            ret.add(get(l));
        }

        return ret;
    }

    private List<Location> getAdjacentLocations(Location loc, int dist) {
        Debug.echo("Get adjacent locations");

        ArrayList<Location> locs = new ArrayList<Location>();

        int row = loc.row;
        int col = loc.col;

        for (int i = row - dist; i < row + dist; i++) {
            for (int j = col - dist; j < col + dist; j++) {
                if (inBounds(i, j) && distance(row, col, i, j) <= dist) {
                    locs.add(new Location(i, j));
                }
            }
        }

        return locs;
    }

    public List<GridSquare> getEmptyLocations(int row, int col, int distance) {
        Debug.echo("Get empty locations");

        List<GridSquare> locs = getAdjacentGridSquares(new Location(row, col), distance);
        ArrayList<GridSquare> ret  = new ArrayList<GridSquare>();

        for (GridSquare l : locs) {
            if (l.getAnimal() == null) ret.add(l);
        }

        return ret;
    }

    public List<GridSquare> getOccupiedLocations(int row, int col, int distance) {
        Debug.echo("Get occupied locations");

        List<GridSquare> locs = getAdjacentGridSquares(new Location(row, col), distance);
        ArrayList<GridSquare> ret  = new ArrayList<GridSquare>();

        for (GridSquare l : locs) {
            if (l.getAnimal() != null) ret.add(l);
        }

        return ret;
    }

    public GridSquare[][]  getGridSquares()   { return gridSquares;   }
    public GridSummary[][] getGridSummaries() { return gridSummaries; }
    public int getGridSize() { return gridSquares.length; }
    public Location[] getLocations() { return locations; }

    public String toString() {
        String terrain = "";
        String animals = "";

        for (int i = 0; i < getGridSize(); i++) {
            for (GridSquare s : gridSquares[i]) {
                if (s.getPlant() != null) {
                    terrain += Simulation.classnameToSymbol(s.getPlant().getClass().getName());
                } else {
                    terrain += "_";
                }

                if (s.getAnimal() != null) {
                    animals += Simulation.classnameToSymbol(s.getAnimal().getClass().getName());
                } else {
                    animals += "_";
                }
            }
            terrain += "\n";
            animals += "\n";
        }

        return terrain + "\n" + animals;
    }
}
