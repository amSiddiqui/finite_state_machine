package com.tks;

public class DFA {
    private String name;
    private char terminals[];
    private int numberOfStates;
    private int initialState;
    private int finalStates[];
    private int transitionFunction[][];
    private int currentState;

    public DFA() {
        this.terminals = new char[0];
        this.numberOfStates = 0;
        this.initialState = -1;
        this.finalStates = new int[0];
        this.currentState = 0;
        this.transitionFunction = new int[0][0]; 
    }

    public DFA(String name, char terminals[], int numberOfStates, int finalStates[], int transitionFunction[][]) {
        this.name = name;
        this.terminals = terminals;
        this.numberOfStates = numberOfStates;
        this.initialState = 0;
        this.finalStates = finalStates;
        this.transitionFunction = transitionFunction;
        this.currentState = this.initialState;
    }

    public DFA(String name, char terminals[], int numberOfStates, int finalStates[], int transitionFunction[][], int initialState) {
        this(name, terminals, numberOfStates, finalStates, transitionFunction);
        this.initialState = initialState;
        this.currentState = this.initialState;
    }

    public int nextState(char terminal) {
        int tIndex = linearSearch(this.terminals, terminal);
        int newState = this.transitionFunction[currentState][tIndex];
        this.currentState = newState;
        return newState;
    }

    public boolean isFinalSate(int state) {
        for (int i = 0; i < finalStates.length; i++) {
            if (finalStates[i] == state) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        this.currentState = this.initialState;
    }

    private int linearSearch (char arr[], char val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public char[] getTerminals() {
        return terminals;
    }

    public void setTerminals(char[] terminals) {
        this.terminals = terminals;
    }

    public int getNumberOfStates() {
        return numberOfStates;
    }

    public void setNumberOfStates(int numberOfStates) {
        this.numberOfStates = numberOfStates;
    }

    public int getInitialState() {
        return initialState;
    }

    public void setInitialState(int initialState) {
        this.initialState = initialState;
    }

    public int[] getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(int[] finalStates) {
        this.finalStates = finalStates;
    }

    public int[][] getTransitionFunction() {
        return transitionFunction;
    }

    public void setTransitionFunction(int[][] transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}