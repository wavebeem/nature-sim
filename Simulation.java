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

    public void parseFoodWeb(File file) {
        Debug.echo("Here is where I would parse the food web file");

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] contents = line.split(":");

                // get name of predator
                String predator = contents[0].trim();

                // get names of prey
                ArrayList<String> prey = new ArrayList<String>;
                for (int i = 1; i < contents.length; i++) {
                    prey.add(contents[i].trim());
                }

                for (String p : prey) {
                    if      (predator.equals("Rabbit") Rabbit.addPrey(p);
                    else if (predator.equals("Fox")    Fox.addPrey(p);

                    // Note: Plants don't need to know their predators
                    if      (p.equals("Rabbit") Rabbit.addPredator(p);
                    else if (p.equals("Fox")    Fox.addPredator(p);
                }
            }
        } catch (FileNotFoundException e) {
        }
    }

    public Grid getGrid() {
        return grid;
    }
}
