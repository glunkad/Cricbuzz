package com.example.Cricbuzz.exception;

public class MatchNotFoundException extends  RuntimeException{
    private static final  long serialVersionUID = 1;

    public MatchNotFoundException(String message){
        super(message);
    }
}
