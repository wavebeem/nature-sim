public class GridSquare {
    private Plant plant;
    private Animal animal;
    private Location location;

    public GridSquare(Location loc) {
        Debug.echo("Making a GridSquare");
        location = loc;
    }

    public GridSquare(Location loc, Plant p, Animal a) {
        Debug.echo("Making a GridSquare with arguments");
        plant  = p;
        animal = a;
        location = loc;
    }

    public Plant getPlant()   { return plant;  }
    public Animal getAnimal() { return animal; }
    public Location getLocation() { return location; }

    public void setPlant(Plant p)   { plant = p;  }
    public void setAnimal(Animal a) { animal = a; }
    
    public boolean equals(Object o){
        GridSquare that = (GridSquare)o;
        return this.plant == that.plant && this.animal == that.animal;
    }
}
