package com.tks.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.Gson;
import com.tks.DFA;
import com.tks.generator.IllegalTerminalValueException;

public class Parser {

    private Scanner scan;
    public static final boolean DEBUG = true;
    static final String DIRECTORY = "./DFA";

    public Parser() {
        scan = new Scanner(System.in);
    }

    public Parser(Scanner scan) {
        this.scan = scan;
    }

    public void main() {
        File dir = new File(DIRECTORY);
        if (dir.exists()) {
            String files[] = dir.list();
            if (files.length == 0) {
                System.out.println("No DFA saved yet. Create new");
                System.out.println("---------------------------------------");
                System.out.println();
                return;
            }
            System.out.println("Available DFAs: ");
            for (int i = 0; i < files.length; i++) {
                System.out.println((i+1)+": "+files[i]);
            }
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scan.nextLine().trim());
            while (choice > files.length || choice < 1) {
                System.out.print("Wrong Choice Enter Again: ");
                choice = Integer.parseInt(scan.nextLine().trim());
            } 
            String filename = files[choice-1];
            String jsonText = parseJsonFile(filename);
            Gson gson = new Gson();
            DFA dfa = gson.fromJson(jsonText, DFA.class);
            System.out.println("-----------------------"+dfa.getName()+"--------------------");
            if (dfa.getDescription().length() != 0) {
                System.out.println("Description: "+dfa.getDescription());
            }
            System.out.println("Enter input Terminal. Valid Terminal values: "+Arrays.toString(dfa.getTerminals()));
            String inputTerminal;
            while(true) {
                inputTerminal = scan.nextLine().trim();
                try {
                    if (dfa.validInput(inputTerminal, DEBUG)){
                        System.out.println("INPUT ACCEPTED");
                    } else {
                        System.out.println("INPUT REJECTED");
                    }
                    System.out.print("New Input? (y/n): ");
                    String contChoice = scan.nextLine().trim();
                    if (contChoice.equalsIgnoreCase("y") || contChoice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter Input: ");
                        continue;
                    }
                    break;
                }catch(IllegalTerminalValueException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("Enter input again");
                    continue;
                }
            } 
            
        }
        else {
            System.out.println("No DFA created yet.");
        }
        System.out.println("---------------------------------------");
        System.out.println();
    }


    public String parseJsonFile(String filename) {
        String jsonText = "";
        try{
            File jsonFile = new File(DIRECTORY+"/"+filename);
            FileInputStream fis = new FileInputStream(jsonFile);
            byte[] bytes = new byte[(int) jsonFile.length()];
            fis.read(bytes);
            fis.close();
            jsonText = new String(bytes, "UTF-8");
            
        }catch (IOException ex) {
            System.out.println(ex);
            System.exit(1);
        }
        return jsonText;            
    }
}