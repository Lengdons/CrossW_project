package core;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CharLimit extends PlainDocument {
    private int limit;

    public CharLimit(int limit) {
        this.limit = limit;
    }
    
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;
        if (!str.chars().allMatch(Character::isLetter)) {
            return; 
        }
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str.toUpperCase(), attr);
        }
    }
}