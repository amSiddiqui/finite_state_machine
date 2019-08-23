package com.tks.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tks.DFA;

/*
This class Generates New DFA based on user inputs and saves them in DFA folder
*/
public class Generator {
    private Scanner scan;

    public Generator() {
        scan = new Scanner(System.in);
    }

    public Generator(Scanner scan) {
        this.scan = scan;
    }

    public void main() {
        System.out.println("----------------------- DFA Generaotr -------------------------");
        // Initial a charater Array to store terminals
        char terminals[];
        // Store Number of states
        int numberOfStates;
        // Store Initial State
        // int initialState = 0;
        // Store Final States
        int finalStates[];
        // Store transitionFuntion
        int transitionFuntion[][];
        // Store Name of DFA
        String name;
        // Store description of DFA
        String description;
        
        // Input Name of DFA
        System.out.print("Enter a name of DFA: ");
        // TODO: Check if name already exists
        name = scan.nextLine().trim();
        
        System.out.println("Enter description: ");
        description = scan.nextLine().trim();

        // Replace white spaces with _
        name = name.replace(' ', '_');

        // Input and process terminals
        System.out.println("Enter Terminals (Eg: 1,0 or a,b,c)");
        String rawTerminals;
        while(true) {
            rawTerminals = scan.nextLine().trim();
            try {
                terminals = processRawTerminals(rawTerminals);
                break;
            }catch(IllegalTerminalValueException ex) {
                System.out.println(ex.toString());
                System.out.println("Try Again");
                continue;
            }
        }

        // Input number of states
        System.out.print("Enter number of states: ");
        numberOfStates = Integer.parseInt(scan.nextLine().trim()); 

        // TODO: Add user choice to change initial state

        // Enter finals states
        System.out.println("Enter Final states (e.g. q1,q2,q3): ");
        String rawFinalStates;
        while(true) {
            rawFinalStates = scan.nextLine().trim();
            try {
                finalStates = processRawFinalStates(rawFinalStates, numberOfStates-1);
                break;
            }catch(IllegalStateValueException ex) {
                System.out.println("Entered State Exceeds the maximum number of allowed state");
                System.out.println("Please Enter Again");
                continue;
            }catch(NumberFormatException ex) {
                System.out.println("Wrong input format. State Should be q<number> or Q<number>");
                System.out.println("Please Enter Again");
                continue;
            }
        }

        // Input transition Funtion
        transitionFuntion = new int[numberOfStates][terminals.length];
        for (int i = 0; i < transitionFuntion.length; i++) {
            System.out.println("Enter Transitions for State Q"+i+": ");
            for (int j = 0; j < transitionFuntion[i].length; j++) {
                while (true) {
                    System.out.print("Q"+i+" --"+terminals[j]+"--> ");
                    String rawState = scan.nextLine().trim();
                    try {
                        transitionFuntion[i][j] = processRawState(rawState, numberOfStates-1);
                        break;
                    }catch(IllegalStateValueException ex) {
                        System.out.println("Entered State Exceeds the maximum number of allowed state");
                        System.out.println("Please Enter Again");
                        continue;
                    }catch(NumberFormatException ex) {
                        System.out.println("Wrong input format. State Should be q<number> or Q<number>");
                        System.out.println("Please Enter Again");
                        continue;
                    }
                }
            }
        }

        // Create a new DFA with all the inputs
        DFA dfa = new DFA(name, terminals, numberOfStates, finalStates, transitionFuntion, description);

        // Store this DFA as a json File
        // Create a new Folder called DFA
        File dfaFolder = new File("./DFA");
        dfaFolder.mkdir();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonDfa = gson.toJson(dfa);
        File newDFAFile = new File("./DFA", dfa.getName()+".json");
        try {
            FileOutputStream outputStream = new FileOutputStream(newDFAFile);
            outputStream.write(jsonDfa.getBytes());
            try {
                outputStream.close();
                System.out.println("DFA machine successfully saved in file "+newDFAFile.getAbsolutePath());
            }catch(Exception ex) {
                System.out.println("Exception while closing outputStrem");
                System.out.println(ex);
            }
        }catch(IOException ex) {
            System.out.println("Error Occured while writing");
            System.out.println(ex);
            System.exit(1);
        }

        System.out.println("----------------------------------------------");
        System.out.println();
    }

    private char[] processRawTerminals(String rawTerminals) throws IllegalTerminalValueException {
        StringTokenizer st = new StringTokenizer(rawTerminals, " ,-");
        char processedTerminals[] = new char[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.length() != 1) {
                throw new IllegalTerminalValueException(token);
            }else{
                processedTerminals[i++] = token.charAt(0);
            }
        }
        return processedTerminals;
    }

    private int[] processRawFinalStates(String rawFinalStates, int maxState) throws IllegalStateValueException, NumberFormatException {
        StringTokenizer tokens = new StringTokenizer(rawFinalStates, " ,-");
        int finalState[] = new int[tokens.countTokens()];
        int i = 0;
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            int stateValue = Integer.parseInt(token.substring(1));
            if (stateValue < 0 || stateValue > maxState) {
                throw new IllegalStateValueException();
            }
            else {
                finalState[i++] = stateValue;
            }
        }
        return finalState;
    }

    private int processRawState(String rawState, int maxState) throws IllegalStateValueException, NumberFormatException {
        int state = Integer.parseInt(rawState.substring(1));
        if (state < 0 || state > maxState) {
            throw new IllegalStateValueException();
        }
        return state;
    }
}