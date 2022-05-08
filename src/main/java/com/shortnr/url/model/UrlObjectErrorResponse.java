package com.shortnr.url.model;

public class UrlObjectErrorResponse {
    private String error;
    private String status;

    public UrlObjectErrorResponse(){}

    public UrlObjectErrorResponse(String error, String status){
        this.error = error;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String urlErrorResponseToString(){
        return "UrlObjectErrorResponse{" +
                "error: " + error +
                ", status: " + status + "}";
    }
}
