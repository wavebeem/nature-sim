import java.util.ArrayList;
import java.awt.Image;

public class Fox extends Animal {
    private static final int sightDistance = 10;
    private static final int moveDistance = 2;
    private static final int maxHunger = 100;
    private static final int maxAge = 100;
    
    private static ArrayList<String> prey;
    private static ArrayList<String> predators;
    
    private int hunger;
    private int age;
    
    public Fox(){
        Debug.echo("Constructing a new Fox object");
        
        hunger = 0;
        age = 0;
    }
    public void step(int row, int col, Grid grid){
        Debug.echo("Here is where the Fox would step");
    }
    public Image getImage(){
        return null;
    }
}