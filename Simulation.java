public class Simulation {
    private Grid grid;
    private int stepNumber;
    
    public Simulation(int gridSize) {
        Debug.echo("Constructing a new Simulation object");
        
        grid = new Grid(gridSize);
        stepNumber = 0;
    }
    public Simulation(String gridFilename, String webFilename){ 
        Debug.echo("Constructing a new Simulation object");
        
        grid = new Grid(gridFilename);
        stepNumber = 0;
        parseFoodWeb(webFilename);
    }
    
    public void step() {
        Debug.echo("Here is where the simulation would step through each organism in the grid.");
    }
    public void parseFoodWeb(String filename) {
        Debug.echo("Here is where I would parse the food web file");
    }
    public Grid getGrid() {
        return grid;
    }
}
