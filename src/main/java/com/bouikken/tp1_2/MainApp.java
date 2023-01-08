package com.bouikken.tp1_2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * TP1 - Partie 2 : Analyse statique et dynamique
 * Module : HAI913I - Évolution et restructuration des logiciels
 * Option : Génie Logiciel (M2)
 *
 * Nom : Bouikken Bahi Amar
 * Prénom : Abdelmadjid
 * Email : bouikkenmajid@gmail.com
 *
 * Encadrant: M. Abdelhak-Djamel Seriai
 */
public class MainApp {
    static String path;
    public final static String indentationFormat = "\s|\s-\t";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        if (args.length > 0) {
            MainApp.path = args[0];
            MainApp.Menu();
        } else {
            System.out.println("Veuillez insérer le chemin du projet OO à analyser en argument");
        }
    }

    public static void Menu() throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        Processor processor;
        Gui gui;
        processor = new Processor(MainApp.path);
        processor.process();
        processor.processGraph();
        processor.saveGraph();
        processor.saveGraphAsPNG();
        while (true) {
            System.out.println(
                    "******** TP1 - Partie 2 : Analyse statique et dynamique *******");
            System.out.println("1. Les informations des classes du projet (infos de la partie 2 du TP)");
            System.out.println("2. Pour la version graphique de l'exercice 1");
            System.out.println("3. Pour le graphe d'appels");
            System.out.println("4. Pour la version graphique de l'exercice 2");
            System.out.println("0. Pour quitter");
            System.out.println("");
            System.out.print("Veuillez introduire votre choix : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    processor.display();
                }
                case 2 -> {
                    System.out.println("La fenêtre va s'ouvrir !");
                    gui = new Gui(processor);
                }
                case 3 -> {
                    System.out.println("Le graphe d'appels est :");
                    System.out.println(processor.getGraph());
                }
                case 4 -> {
                    System.out.println("La fenêtre va s'ouvrir !");
                    gui = new Gui();
                    gui.showGraph();
                }
                case 0 -> {
                    System.out.println("Au revoir ! ");
                    sc.close();
                    System.exit(0);
                }
                default -> {
                }
            }
        }
    }
}
