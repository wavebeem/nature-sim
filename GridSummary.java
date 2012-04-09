import java.util.ArrayList;

public class GridSummary {
    private ArrayList<GridSquare> gridSquares;

    public GridSummary() {
        Debug.echo("Making a GridSummary");
        gridSquares = new ArrayList<GridSquare>();
    }

    public GridSummary(ArrayList<GridSquare> g) {
        Debug.echo("Making a GridSummary with arguments");
        gridSquares = g;
    }

    public ArrayList<Organism> getPrevalentOrganisms(int n) {
        Debug.echo("Getting n most prevalent organisms");
    }
}
