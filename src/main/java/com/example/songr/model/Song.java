package com.example.songr.model;

import javax.persistence.*;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer length;
    private Integer trackNumber;
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album songAlbum;

    public Song(String title, Integer length, Integer trackNumber, Album songAlbum) {
        this.title = title;
        this.length = length;
        this.trackNumber = trackNumber;
        this.songAlbum = songAlbum;
    }

    public Song(){

    }

    public Long getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Album getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(Album songAlbum) {
        this.songAlbum = songAlbum;
    }
}
