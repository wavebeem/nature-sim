import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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

    public void removeAnimal(Location loc) {
        Debug.echo("Removing animal at " + loc);
        gridSquares[loc.row][loc.col].setAnimal(null);
    }

    private boolean inBounds(int row, int col) {
        return row >= 0 && row < getGridSize()
            && col >= 0 && col < getGridSize();
    }

    private int distance(int x, int y, int i, int j) {
        return Math.abs(x - i) + Math.abs(y - j);
    }

    @SuppressWarnings("unchecked")
    public List<DistanceSquarePair> getAdjacentSquares(Location loc, int dist) {
        Debug.echo("Get adjacent gridSquares");

        ArrayList<DistanceSquarePair> ret = new ArrayList<DistanceSquarePair>();

        int row = loc.row;
        int col = loc.col;

        for (int i = row - dist; i < row + dist; i++) {
            for (int j = col - dist; j < col + dist; j++) {
                int d = distance(row, col, i, j);
                if (inBounds(i, j) && d <= dist && d != 0) {
                    ret.add(new DistanceSquarePair(d, get(i, j)));
                }
            }
        }
        Collections.sort(ret);
        return ret;
    }

    public List<DistanceSquarePair> getEmptySquares(int row, int col, int distance) {
        Debug.echo("Get empty locations");

        List<DistanceSquarePair> squares = getAdjacentSquares(new Location(row, col), distance);
        ArrayList<DistanceSquarePair> ret  = new ArrayList<DistanceSquarePair>();

        for (DistanceSquarePair s : squares) {
            if (s.gridSquare.getAnimal() == null) ret.add(s);
        }

        return ret;
    }

    public List<DistanceSquarePair> getEmptySquares(List<DistanceSquarePair> squares) {
        ArrayList<DistanceSquarePair> ret  = new ArrayList<DistanceSquarePair>();

        for (DistanceSquarePair s : squares) {
            if (s.gridSquare.getAnimal() == null) ret.add(s);
        }

        return ret;
    }

    public List<DistanceSquarePair> getOccupiedSquares(int row, int col, int distance) {
        Debug.echo("Get occupied locations");

        List<DistanceSquarePair> squares = getAdjacentSquares(new Location(row, col), distance);
        ArrayList<DistanceSquarePair> ret  = new ArrayList<DistanceSquarePair>();

        for (DistanceSquarePair s : squares) {
            if (s.gridSquare.getAnimal() != null) ret.add(s);
        }

        return ret;
    }

    public List<DistanceSquarePair> getOccupiedSquares(List<DistanceSquarePair> squares) {
        ArrayList<DistanceSquarePair> ret  = new ArrayList<DistanceSquarePair>();

        for (DistanceSquarePair s : squares) {
            if (s.gridSquare.getAnimal() != null) ret.add(s);
        }

        return ret;
    }

    public List<DistanceSquarePair> getOrganismSquares(List<DistanceSquarePair> squares, List<String> organismNames) {
        ArrayList<DistanceSquarePair> ret = new ArrayList<DistanceSquarePair>();

        for (DistanceSquarePair s : squares) {
            if (s.gridSquare.getAnimal() != null) {
                String name = s.gridSquare.getAnimal().getClass().getName();
                if (organismNames.contains(name)) {
                    ret.add(s);
                }
            }
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
