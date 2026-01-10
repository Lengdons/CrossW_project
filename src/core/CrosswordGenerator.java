package core;

import java.util.ArrayList;
import java.util.List;

public class CrosswordGenerator {

	// vardu novietojums uz lauka 
    public static class PlacedWord {
        String word;
        int row, col;
        boolean isVertical;

        public PlacedWord(String word, int row, int col, boolean isVertical) {
            this.word = word;
            this.row = row;
            this.col = col;
            this.isVertical = isVertical;
        }
    }

    public boolean generate(CrosswordGrid grid, List<String> words, List<PlacedWord> result) {
        if (words.isEmpty()) {
            return true;
        }

        String currentWord = words.get(0);
        List<String> remainingWords = new ArrayList<>(words.subList(1, words.size()));

        // iziet cauri katrai šūnai laukā
        int size = 15;
        
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                // Horizontāli
                if (grid.canPlaceWord(currentWord, row, col, false)) {
                    grid.placeWord(currentWord, row, col, false);
                    result.add(new PlacedWord(currentWord, row, col, false));

                    if (generate(grid, remainingWords, result)) {
                        return true;
                    }

                    grid.removeWord(currentWord, row, col, false);
                    result.remove(result.size() - 1);
                }

                // Vertikāli
                if (grid.canPlaceWord(currentWord, row, col, true)) {
                    grid.placeWord(currentWord, row, col, true);
                    result.add(new PlacedWord(currentWord, row, col, true));

                    if (generate(grid, remainingWords, result)) {
                        return true;
                    }

                    grid.removeWord(currentWord, row, col, true);
                    result.remove(result.size() - 1);
                }
            }
        }

        return false; // Ja nevar vārdu nekur ielikt
    }
}