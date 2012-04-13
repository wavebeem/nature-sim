public class Location {
    public int row;
    public int col;
    
    public Location(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public boolean equals(Object o) {
        Location that = (Location)o;
        return (this.row == that.row) && (this.col == that.col);
    }
    public String toString(){
        return "("+row+","+col+")";
    }
}