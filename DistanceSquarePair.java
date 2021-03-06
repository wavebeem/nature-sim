public class DistanceSquarePair implements Comparable{
    public final Integer distance;
    public final GridSquare gridSquare;
    
    public DistanceSquarePair(Integer distance, GridSquare gridSquare){
        this.distance = distance;
        this.gridSquare = gridSquare;
    }
    
    public boolean equals(Object o) {
        DistanceSquarePair that = (DistanceSquarePair)o;
        return (this.distance == that.distance && this.gridSquare.equals(that.gridSquare));
    }
    public int compareTo(Object o){
        DistanceSquarePair that = (DistanceSquarePair)o;
        return this.distance.compareTo(that.distance);
    }
    public String toString(){
        return "Distance: "+distance+" Loc: "+gridSquare.getLocation();
    }
}