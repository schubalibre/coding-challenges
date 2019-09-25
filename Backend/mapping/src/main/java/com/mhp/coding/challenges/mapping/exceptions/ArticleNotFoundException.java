package com.mhp.coding.challenges.mapping.exceptions;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(Long id) {
        super("article# " + id + " was not found");
    }
}
