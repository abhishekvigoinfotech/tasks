package com.example.practice.models;

public class SQLiteModel {
    private int id;
    private String timestamp;
    private String url;
    private String response;

    public SQLiteModel(String timestamp, String url, String response) {
        this.timestamp = timestamp;
        this.url = url;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
