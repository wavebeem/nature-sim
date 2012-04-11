import java.util.Scanner;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;

public class Simulation {
    private Grid grid;
    private int stepNumber;
    private Map<String, String> symbolMap;
    
    public Simulation(int gridSize) {
        Debug.echo("Constructing a new Simulation object");

        parseSymbolMap();
        System.out.println(symbolMap.keySet()+"\n"+symbolMap.values());
        grid = new Grid(gridSize);
        stepNumber = 0;
    }
    public Simulation(File animals, File terrain, File foodweb){ 
        Debug.echo("Constructing a new Simulation object");
        
        parseSymbolMap();
        parseGrid(animals, terrain);
        parseFoodWeb(foodweb);
        stepNumber = 0;
    }
    
    public void step() {
        Debug.echo("Here is where the simulation would step through each organism in the grid.");
    }
    public void parseSymbolMap()
        symbolMap = new HashMap<String, String>();
        try {
            Scanner scanner = new Scanner(new File("resources/symbols.dat"));
            String symbol, className, line;
            String[] words;
            while(scanner.hasNext()){
                line = scanner.nextLine().trim();
                words = line.split("\\s+");
                symbol = words[0];
                className = words[1];
                symbolMap.put(symbol, className);
            }
        } catch (FileNotFoundException e) {
            Debug.echo("SymbolMap: File not found!");
        } catch (Exception e) {
            Debug.echo("SymbolMap: Invalid file format!");
        }
        
    }
    public void parseFoodWeb(File foodweb) {
        Debug.echo("Here is where I would parse the food web file");
    }
    public void parseGrid(File animals, File terrain) {
        //grid = new Grid(gridFilename);
        Debug.echo("Parsing grid from file");
    }
    public Grid getGrid() {
        return grid;
    }
}
