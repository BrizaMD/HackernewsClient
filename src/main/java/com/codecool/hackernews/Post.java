package com.codecool.hackernews;

public class Post {
    int id;
    String title;
    int points;
    String user;
    int time;
    String time_ago;
    int comments_count;
    String type;
    String url;
    String domain;

    public Post(int id, String title, int points, String user, int time, String time_ago, int comments_count, String type, String url, String domain) {
        this.id = id;
        this.title = title;
        this.points = points;
        this.user = user;
        this.time = time;
        this.time_ago = time_ago;
        this.comments_count = comments_count;
        this.type = type;
        this.url = url;
        this.domain = domain;
    }
}
