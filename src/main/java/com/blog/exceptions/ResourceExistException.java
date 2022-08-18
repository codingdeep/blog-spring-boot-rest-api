package com.blog.exceptions;

public class ResourceExistException extends RuntimeException {
    private String resourceName;
    private String resourceLabel;
    private String resourceValue;

    public ResourceExistException(String resourceName, String resourceLabel, String resourceValue) {
        super(String.format("%s is already exist with this %s : %s",resourceName,resourceLabel,resourceValue));
        this.resourceName = resourceName;
        this.resourceLabel = resourceLabel;
        this.resourceValue = resourceValue;
    }
}
