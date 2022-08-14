package com.springboot.exceptions;

public class ItemNotExistException extends IllegalArgumentException {
    public ItemNotExistException(String msg) {
        super(msg);
    }
}