package core;
//Rihards
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

        addBtn.addActionListener(e -> {
            String w = wordField.getText().trim().toUpperCase();
            String d = descField.getText().trim();

            if (!w.isEmpty() && !d.isEmpty()) {
                if (!w.chars().allMatch(Character::isLetter)) {
                    JOptionPane.showMessageDialog(this, "Vārds drīkst saturēt tikai burtus!");
                    return;
                }

                customDictionary.put(w, d);
                listModel.addElement(w + " : " + d);
                wordField.setText("");
                descField.setText("");
                wordField.requestFocus();
            }
        });
        startBtn.addActionListener(e -> {
            if (customDictionary.size() < 2) {
                JOptionPane.showMessageDialog(this, "Lūdzu pievienojiet vismaz 2 vārdus!");
                return;
            }
            new MenuWindow().startTheGame(customDictionary);
            this.dispose();
        });

        backBtn.addActionListener(e -> {
            new MenuWindow();
            this.dispose();
        });

        btnPanel.add(backBtn);
        btnPanel.add(addBtn);
        btnPanel.add(startBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}