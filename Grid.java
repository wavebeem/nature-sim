import java.util.List;
import java.util.ArrayList;

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

    public List<GridSquare> getAdjacentLocations(int row, int col, int distance) {
        Debug.echo("Get adjacent locations");

        ArrayList<GridSquare> locs = new ArrayList<GridSquare>();

        // look at left
        int r = row, c = col - 1;
        if (inBounds(r, c)) locs.add(get(r, c));

        // look at right
        r = row;
        c = col + 1;
        if (inBounds(r, c)) locs.add(get(r, c));

        // look up
        r = row - 1;
        c = col;
        if (inBounds(r, c)) locs.add(get(r, c));

        // look down
        r = row + 1;
        c = col;
        if (inBounds(r, c)) locs.add(get(r, c));

        distance--;

        if (distance > 0) {
            for (GridSquare l : locs) {
                locs.addAll(getAdjacentLocations(row, col, distance));
            }
        }

        return locs;
    }

    public List<GridSquare> getEmptyLocations(int row, int col, int distance) {
        Debug.echo("Get empty locations");

        List<GridSquare> locs = getAdjacentLocations(row, col, distance);
        ArrayList<GridSquare> ret  = new ArrayList<GridSquare>();

        for (GridSquare l : locs) {
            if (l.getAnimal() == null) ret.add(l);
        }

        return ret;
    }

    public List<GridSquare> getOccupiedLocations(int row, int col, int distance) {
        Debug.echo("Get occupied locations");

        List<GridSquare> locs = getAdjacentLocations(row, col, distance);
        ArrayList<GridSquare> ret  = new ArrayList<GridSquare>();

        for (GridSquare l : locs) {
            if (l.getAnimal() != null) ret.add(l);
        }

        return ret;
    }

    public GridSquare[][]  getGridSquares()   { return gridSquares;   }
    public GridSummary[][] getGridSummaries() { return gridSummaries; }
    public int getGridSize() { return gridSquares.length; }

    public String toString() {
        String terrain = "";
        String animals = "";

        for (int i = 0; i < getGridSize(); i++) {
            for (GridSquare s : gridSquares[i]) {
                if (s.getPlant() != null) {
                    terrain += Simulation.classnameToSymbol(s.getPlant().toString());
                } else {
                    terrain += "_";
                }

                if (s.getAnimal() != null) {
                    animals += Simulation.classnameToSymbol(s.getAnimal().toString());
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
