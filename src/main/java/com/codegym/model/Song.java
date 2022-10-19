package com.codegym.model;

public class Song {
    private int id;
    private String name;
    private String author;
    private String category;
    private String file;
    private boolean checkfilemp3;

    public Song(int id, String name, String author, String category, String file) {
        this.checkfilemp3 = CheckFileMp3.checkFile(file);
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.file = file;
    }

    public Song(String name, String author, String category, String file) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.file = file;
        this.checkfilemp3 = CheckFileMp3.checkFile(file);
    }

    public Song() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean isCheckfilemp3() {
        return CheckFileMp3.checkFile(this.file);
    }

}
