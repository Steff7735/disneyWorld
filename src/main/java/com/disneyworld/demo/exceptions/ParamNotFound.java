package com.disneyworld.demo.exceptions;

public class ParamNotFound extends RuntimeException {

    public ParamNotFound(String error) {
        super(error);
    }
}
