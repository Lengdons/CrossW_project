package core;

// konteineris vārdam
public class placedWord {
	public String word;
    public int row;
    public int col;
    public boolean isVertical;
    
    public placedWord(String word, int row, int col, boolean isVertical) {
        this.word = word;
        this.row = row;
        this.col = col;
        this.isVertical = isVertical;
    }
}
