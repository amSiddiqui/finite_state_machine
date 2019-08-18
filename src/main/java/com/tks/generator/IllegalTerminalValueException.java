package com.tks.generator;

// Exception when a terminal value is wrong

public class IllegalTerminalValueException extends Exception {
    private static final long serialVersionUID = -5096688783970900165L;

    public IllegalTerminalValueException() {
        super("Illegal Vlaue Of Terminal");
    }

    public IllegalTerminalValueException(String value) {
        super("Illegal Value of "+value);
    }
}