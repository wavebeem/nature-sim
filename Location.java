public class Location implements Comparable{
    public final Integer row;
    public final Integer col;
    
    public Location(Integer row, Integer col){
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
    public int compareTo(Object o){
        Location that = (Location)o;
        int returnVal = this.row.compareTo(that.row);
        if(returnVal == 0){
            return this.col.compareTo(that.col);
        } else {
            return returnVal;
        }
    }
}