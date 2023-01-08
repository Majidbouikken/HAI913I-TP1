package com.bouikken.tp1_2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/// Interface graphique pour répondre a la question optionnelle
public class Gui extends JFrame implements ActionListener {
    public Processor processeur;

    private final JPanel pan = new JPanel();
    /// Boutons de l'exercice 1
    private final JButton button1 = new JButton("1. Nombre de classes de l’application");
    private final JButton button2 = new JButton("2. Nombre de lignes de code de l'application");
    private final JButton button3 = new JButton("3. Nombre total de méthodes de l'application");
    private final JButton button4 = new JButton("4. Nombre total de packages de l'application");
    private final JButton button5 = new JButton("5. Nombre moyen de méthodes par classe");
    private final JButton button6 = new JButton("6. Nombre moyen de lignes de code par méthode");
    private final JButton button7 = new JButton("7. Nombre moyen d'attributs par classe");
    private final JButton button8 = new JButton("8. Les 10% des classes qui possèdent le plus grand nombre de méthodes");
    private final JButton button9 = new JButton("9. Les 10% des classes qui possèdent le plus grand nombre d'attributs");
    private final JButton button10 = new JButton("10. Les classes qui font partie en meme temps des deux categories précédentes");
    private final JButton button11 = new JButton("11. Les classes qui possèdent plus de X méthodes");
    private final JButton button12 = new JButton("12. Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe)");
    private final JButton button13 = new JButton("13. Le nombre maximal de paramètres par rapport a toutes les méthodes de l'application");

    public Gui(Processor processeur) {
        this.processeur = processeur;
        this.setTitle("TP1 - Partie 2 - Exercice 1");
        this.setSize(600, 400); // Taille de la fenêtre
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Bouton 1
        button1.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button1);
        this.button1.addActionListener(this);
        // Bouton 2
        button2.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button2);
        this.button2.addActionListener(this);
        // Bouton 3
        button3.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button3);
        this.button3.addActionListener(this);
        // Bouton 4
        button4.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button4);
        this.button4.addActionListener(this);
        // Bouton 5
        button5.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button5);
        this.button5.addActionListener(this);
        // Bouton 6
        button6.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button6);
        this.button6.addActionListener(this);
        // Bouton 7
        button7.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button7);
        this.button7.addActionListener(this);
        // Bouton 8
        button8.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button8);
        this.button8.addActionListener(this);
        // Bouton 9
        button9.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button9);
        this.button9.addActionListener(this);
        // Bouton 10
        button10.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button10);
        this.button10.addActionListener(this);
        // Bouton 11
        button11.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button11);
        this.button11.addActionListener(this);
        // Bouton 12
        button12.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button12);
        this.button12.addActionListener(this);
        // Bouton 13
        button13.setHorizontalAlignment(SwingConstants.LEFT);
        pan.add(button13);
        this.button13.addActionListener(this);

        this.setContentPane(pan);
        this.setVisible(true);

        this.setLayout(new GridLayout(13, 1));
        this.setVisible(true);
    }

    public Gui() {}

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String res;
        if (arg0.getSource().equals(button1)) {
            res = processeur.exercice1();
            JOptionPane.showMessageDialog(pan, "Nombre de classes de l’application: "+res, "Question 1", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button2)) {
            res = processeur.exercice2();
            JOptionPane.showMessageDialog(pan, "Nombre de lignes de code de l'application: "+res, "Question 2", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button3)) {
            res = processeur.exercice3();
            JOptionPane.showMessageDialog(pan, "Nombre total de méthodes de l'application: "+res, "Question 3", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button4)) {
            res = processeur.exercice4();
            JOptionPane.showMessageDialog(pan, "Nombre total de packages de l'application: "+res, "Question 4", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button5)) {
            res = processeur.exercice5();
            JOptionPane.showMessageDialog(pan, "Nombre moyen de méthodes par classe: "+res, "Question 5", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button6)) {
            res = processeur.exercice6();
            JOptionPane.showMessageDialog(pan, "Nombre moyen de lignes de code par méthode: "+res, "Question 6", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button7)) {
            res = processeur.exercice7();
            JOptionPane.showMessageDialog(pan, "Nombre moyen d'attributs par classe: "+res, "Question 7", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button8)) {
            res = processeur.exercice8();
            JOptionPane.showMessageDialog(pan, "Les 10% des classes qui possèdent le plus grand nombre de méthodes: "+res, "Question 8", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button9)) {
            res = processeur.exercice9();
            JOptionPane.showMessageDialog(pan, "Les 10% des classes qui possèdent le plus grand nombre d'attributs: "+res, "Question 9", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button10)) {
            res = processeur.exercice10();
            JOptionPane.showMessageDialog(pan, "Les classes qui font partie en meme temps des deux categories précédentes: "+res, "Question 10", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button11)) {
            String val = JOptionPane.showInputDialog("Nombre de méthodes?");
            int valInt = Integer.parseInt(val);
            res = processeur.exercice11(valInt);
            JOptionPane.showMessageDialog(pan, "Les classes qui possèdent plus de "+valInt+" méthodes: "+res, "Question 11", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button12)) {
            res = processeur.exercice12();
            JOptionPane.showMessageDialog(pan, "Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe):"+res, "Question 12", JOptionPane.PLAIN_MESSAGE);
        } else if (arg0.getSource().equals(button13)) {
            res = processeur.exercice13();
            JOptionPane.showMessageDialog(pan, "Le nombre maximal de paramètres par rapport a toutes les méthodes de l'application: "+res, "Question 13", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void showGraph() {
        JFrame frame = new JFrame();
        this.setTitle("TP1 - Partie 2 - Exercice 2");
        ImageIcon icon = new ImageIcon("graph.png");
        JLabel label = new JLabel(icon);
        JScrollPane scrollPane = new JScrollPane(label);
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }

}