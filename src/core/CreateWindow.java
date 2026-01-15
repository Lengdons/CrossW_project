package core;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CreateWindow extends JFrame {
    private Map<String, String> customDictionary = new HashMap<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public CreateWindow() {
        setTitle("Izveidot Mīklu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel left = new JPanel(new BorderLayout());
        left.add(new JLabel("Vārds (Word):"), BorderLayout.NORTH);
        JTextField wordField = new JTextField();
        left.add(wordField, BorderLayout.CENTER);
  
        JPanel right = new JPanel(new BorderLayout());
        right.add(new JLabel("Apraksts (Description):"), BorderLayout.NORTH);
        JTextField descField = new JTextField();
        right.add(descField, BorderLayout.CENTER);

        inputPanel.add(left);
        inputPanel.add(right);

        JList<String> displayList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(displayList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Pievienotie Vārdi:"));

        JPanel btnPanel = new JPanel();
        
        JButton addBtn = new JButton("Pievienot Vārdu");
        JButton startBtn = new JButton("Sākt Spēli");
        JButton backBtn = new JButton("Atpakaļ");

        addBtn.addActionListener(e -> addWord(wordField, descField));
        wordField.addActionListener(e -> addWord(wordField, descField));
        descField.addActionListener(e -> addWord(wordField, descField));
        startBtn.addActionListener(e -> {
            if (customDictionary.size() < 2) {
                JOptionPane.showMessageDialog(this, "Lūdzu ievietojiet vismaz 2 vārdus!", null, JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Generate the crossword
            CrosswordGenerator builder = new CrosswordGenerator();
            KrustvarduMikla game = builder.generate(new ArrayList<>(customDictionary.keySet()), 20);

            // Open game window directly
            new Window(game, customDictionary).setVisible(true);

            // Close create window AFTER opening game
            this.dispose();
        });


        backBtn.addActionListener(e -> {
            new MenuWindow();
            this.dispose();
        });
        
        JButton editBtn = new JButton("Rediģēt izvēlēto vārdu");
        editBtn.addActionListener(e -> {
            int selectedIndex = displayList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Lūdzu izvēlieties vārdu sarakstā!", null, JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Extract the current word and definition
            String selectedEntry = listModel.get(selectedIndex);
            String[] parts = selectedEntry.split(" : ");
            String oldWord = parts[0].trim();
            String oldDef = parts[1].trim();

            // Edit the word
            String newWord = (String) JOptionPane.showInputDialog(
                    this,
                    "Rediģēt vārdu:",
                    "Rediģēt",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    oldWord
            );

            if (newWord == null) return; // user cancelled
            newWord = newWord.trim().toUpperCase();

            if (newWord.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vārds nevar būt tukšs!", null, JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (newWord.length() < 2) {
                JOptionPane.showMessageDialog(this, "Vārds nedrīkst būt īsāks par diviem burtiem!", null, JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!newWord.chars().allMatch(Character::isLetter)) {
                JOptionPane.showMessageDialog(this, "Vārds drīkst saturēt tikai burtus!", null, JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Edit the definition
            String newDef = (String) JOptionPane.showInputDialog(
                    this,
                    "Rediģēt definīciju priekš " + newWord + ":",
                    "Rediģēt",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    oldDef
            );

            if (newDef == null) return; // cancel
            newDef = newDef.trim();
            if (newDef.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Definīcija nevar būt tukša!", null, JOptionPane.WARNING_MESSAGE);
                return;
            }

            // aizvieto veco vardu ar jauno
            if (!newWord.equals(oldWord)) {
                // ja jaunais vards nav vienads ar veco vardu
                customDictionary.remove(oldWord);
            }
            customDictionary.put(newWord, newDef);
            listModel.set(selectedIndex, newWord + " : " + newDef);
        });


        btnPanel.add(backBtn);
        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(startBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
    
    private void addWord(JTextField wordField, JTextField descField) {
        String w = wordField.getText().trim().toUpperCase();
        String d = descField.getText().trim();

        if (w.isEmpty() || d.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nedrīkst atstāt tukšas ailes", null, JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!w.chars().allMatch(Character::isLetter)) {
            JOptionPane.showMessageDialog(this, "Vārds drīkst saturēt tikai burtus!", null, JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (w.length() < 2) {
            JOptionPane.showMessageDialog(this, "Vārds nedrīkst būt īsāks par diviem burtiem", null, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (customDictionary.containsKey(w)) {
            JOptionPane.showMessageDialog(this, "Šis vārds jau eksistē!", null, JOptionPane.WARNING_MESSAGE);
            return;
        }

        customDictionary.put(w, d);
        listModel.addElement(w + " : " + d);

        wordField.setText("");
        descField.setText("");
        wordField.requestFocus();
    }

}