package com.company.model;

import java.io.Serializable;

public class NoteModel implements Serializable {

    private String title;
    private String content;
    private String date;

    public NoteModel(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    @Override
    public String toString() {
        return  "Note:" +
                "title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", date='" + date + '\'';
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}

