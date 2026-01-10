package core;

import java.util.Arrays;

public class CrosswordGrid {
    private char[][] grid;
    private int size;
    private static final char EMPTY = '-';

    public CrosswordGrid(int size) {
        this.size = size;
        this.grid = new char[size][size];
        for (char[] row : grid) {
            Arrays.fill(row, EMPTY);
        }
    }

    // Pārbaude, vai vārdu var likt rindā vai kolonnā
    // vertikāli: true = no augshas uz leju, false = horizontāli 
    public boolean canPlaceWord(String word, int row, int col, boolean vertical) {
        if (vertical) {
            if (row + word.length() > size) return false; // Out of bounds
        } else {
            if (col + word.length() > size) return false; // Out of bounds
        }

        for (int i = 0; i < word.length(); i++) {
            int r = row + (vertical ? i : 0);
            int c = col + (vertical ? 0 : i);
            char current = grid[r][c];
            
            // Pārbaude, vai nerodas konflikts starp ievitojamajiem burtiem
            if (current != EMPTY && current != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Vārdu ieliek laukā (Grid'ā)
    
    public void placeWord(String word, int row, int col, boolean vertical) {
        for (int i = 0; i < word.length(); i++) {
            int r = row + (vertical ? i : 0);
            int c = col + (vertical ? 0 : i);
            grid[r][c] = word.charAt(i);
        }
    }

    // Noņem vārdu 
    public void removeWord(String word, int row, int col, boolean vertical) {
        for (int i = 0; i < word.length(); i++) {
            int r = row + (vertical ? i : 0);
            int c = col + (vertical ? 0 : i);
            grid[r][c] = EMPTY; 
        }
    }

    public void printGrid() {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}