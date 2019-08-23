package com.tks;

import java.util.Scanner;

import com.tks.generator.Generator;
import com.tks.parser.Parser;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("------------ Deterministic Finite State Machine --------------");
        // Add Option to view Existing DFA and Creating a new DFA
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Menu: ");
            System.out.println("1: To view existing DFA machines");
            System.out.println("2: To create new DFA");
            System.out.println("3: Exit");
            System.out.print("Choice: ");
            int choice = -1;
            // Keep prompting till user enters a correct value
            while (true) {
                choice = Integer.parseInt(scan.nextLine().trim());
                if (choice < 1 || choice > 3) {
                    System.out.println("Wrong Choice");
                    System.out.print("Enter Again: ");
                    continue;
                } else {
                    break;
                }
            }
            switch (choice) {
            case 1:
                // Start a new Routine of displaying existing DFA and testing them
                new Parser(scan).main();
                break;
            case 2:
                // Start new DFA Routine
                new Generator(scan).main();
                break;
            case 3:
                System.out.println("Thank you for using Finite State Machine Generator");
                scan.close();
                System.exit(0);
                break;
            }
        }
    }
}
