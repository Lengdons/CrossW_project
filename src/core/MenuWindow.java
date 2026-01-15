package core;
//Gustavs
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MenuWindow extends JFrame {

    public MenuWindow() {
        setTitle("Krustvārdu Mīkla - Izvēlne");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout()); 
        getContentPane().setBackground(new Color(222, 168, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); //atstarpe
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("KRUSTVĀRDU MĪKLA");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        add(titleLabel, gbc);

        JButton playBtn = createStyledButton("Spēlēt");
        playBtn.addActionListener(e -> {
            java.sql.Connection conn = sqldatabase.getConnection();
            
            if (conn != null) {
                Map<String, String> dbWords = sqldatabase.getGameWords(conn);
                
                if (!dbWords.isEmpty()) {
                	java.util.List<String> keys = new java.util.ArrayList<>(dbWords.keySet());
                	java.util.Collections.shuffle(keys);
                	Map<String, String> gameWords = new HashMap<>();
                	int wordLimit = Math.min(25, keys.size());
                	for (int i = 0; i < wordLimit; i++) {
                        String key = keys.get(i);
                        gameWords.put(key, dbWords.get(key));
                    }
                    startTheGame(gameWords);
                } else {
                    JOptionPane.showMessageDialog(this, "Datubāze ir tukša!");
                }
            }
        });
        gbc.gridy = 1;
        add(playBtn, gbc);

        JButton createBtn = createStyledButton("Izveidot Savu Mīklu");
        createBtn.addActionListener(e -> {
            new CreateWindow().setVisible(true);
            this.dispose(); // aizvērt
        });
        gbc.gridy = 2;
        add(createBtn, gbc);

        JButton demoBtn = createStyledButton("Spēlēt Demo");
        demoBtn.addActionListener(e -> launchDemo());
        gbc.gridy = 3;
        add(demoBtn, gbc);

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setPreferredSize(new Dimension(250, 45));
        btn.setFocusPainted(false);
        return btn;
    }

    private void launchDemo() {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("INTRODUCTION", "The beginning section of a book or speech");
        dictionary.put("REVIEW", "A formal assessment or critique");
        dictionary.put("ELECTORATE", "All the people in a country who are entitled to vote");
        dictionary.put("ORANGE", "A citrus fruit or a color");
        dictionary.put("FRUITFUL", "Producing good or helpful results");
        dictionary.put("MONETISE", "To earn revenue from an asset");
        dictionary.put("BLUEBERRY", "A small sweet blue-black edible berry");
        dictionary.put("ACCORD", "An official agreement or treaty");
        dictionary.put("IGNITE", "To catch fire or cause to catch fire");
        dictionary.put("SAIL", "Fabric used to catch wind and propel a boat");
        dictionary.put("KITE", "A toy that flies in the wind on the end of a string");

        startTheGame(dictionary);
    }

    public void startTheGame(Map<String, String> dictionary) {
        java.util.List<String> words = new java.util.ArrayList<>(dictionary.keySet());
        
        CrosswordGenerator builder = new CrosswordGenerator();
        KrustvarduMikla game = builder.generate(words, 20);

        new Window(game, dictionary).setVisible(true);
        this.dispose(); 
    }
}