package com.mylaboratory.spring;

public class NotFoundException extends Exception{
    public NotFoundException() {
        super("Not Found");
    }
}
