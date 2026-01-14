package core;

import javax.swing.JTextField;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.Color;

public class CrosswordCell extends JTextField {
    private String cellNumber = ""; // Saglabāt numuru

    public void setCellNumber(String num) {
        this.cellNumber = num;
        repaint(); // Refresh'o šunu
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        if (!cellNumber.isEmpty()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.PLAIN, 9));
            
            g2.drawString(cellNumber, 2, 9); 
        }
    }
}
