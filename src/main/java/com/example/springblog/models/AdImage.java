package com.example.springblog.models;

import javax.persistence.*;

@Entity
@Table(name="ad-images")
public class AdImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

    public AdImage(long id, String path, Ad ad) {
        this.id = id;
        this.path = path;
        this.ad = ad;
    }

    public AdImage() {

    }
}
