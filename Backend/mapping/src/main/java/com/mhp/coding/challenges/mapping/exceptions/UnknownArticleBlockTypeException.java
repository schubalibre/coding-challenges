package com.mhp.coding.challenges.mapping.exceptions;

public class UnknownArticleBlockTypeException extends RuntimeException {

    public UnknownArticleBlockTypeException(String type) {
        super("article block type " + type + " is unknown");
    }
}

