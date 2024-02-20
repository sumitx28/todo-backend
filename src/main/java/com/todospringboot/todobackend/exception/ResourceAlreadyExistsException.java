package com.todospringboot.todobackend.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
    private String resourceName;
    private String feildName;
    private String feildValue;

    public ResourceAlreadyExistsException(String resourceName , String feildName , String feildValue) {
        super(String.format("%s already exists. %s : %s", resourceName , feildName , feildValue));
        this.resourceName = resourceName;
        this.feildName = feildName;
        this.feildValue = feildValue;
    }
}
