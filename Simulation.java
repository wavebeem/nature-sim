public class Simulation {
    private Grid grid;
    private int stepNumber;
    
    public Simulation(int gridSize) {
        Debug.echo("Constructing a new Simulation object");
        
        grid = new Grid(gridSize);
        stepNumber = 0;
    }
    public Simulation(File animals, File terrain, File foodweb){ 
        Debug.echo("Constructing a new Simulation object");
        
        parseGrid(animals, terrain);
        parseFoodWeb(foodweb);
        stepNumber = 0;
    }
    
    public void step() {
        Debug.echo("Here is where the simulation would step through each organism in the grid.");
    }
    public void parseFoodWeb(File foodweb) {
        Debug.echo("Here is where I would parse the food web file");
    }
    public void parseGrid(File animals, File terrain) {
        grid = new Grid(gridFilename);
        Debug.echo("Parsing grid from file");
    }
    public Grid getGrid() {
        return grid;
    }
}
