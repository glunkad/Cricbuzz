package com.example.Cricbuzz.exception;

public class TeamNotFoundException extends RuntimeException{
    private static final  long serialVersionUID = 3;

    public TeamNotFoundException(String message){
        super(message);
    }
}
