package com.example.flight_reservation_system;

public class Document {
    private String id;
    private String name;
    private String filePath;
    private String thumbnailPath;

    // Constructor
    public Document(String id, String name, String filePath, String thumbnailPath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
        this.thumbnailPath = thumbnailPath;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getFilePath() { return filePath; }
    public String getThumbnailPath() { return thumbnailPath; }
}

