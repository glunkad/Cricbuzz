package com.example.Cricbuzz.exception;

public class PlayerNotFoundException extends RuntimeException{
    private static final  long serialVersionUID = 2;

    public PlayerNotFoundException(String message){
        super(message);
    }
}
