package com.tks.generator;

class IllegalStateValueException extends Exception {
    private static final long serialVersionUID = 6542880900138996990L;

    IllegalStateValueException() {
        super("State Value is wrong");
    }
}