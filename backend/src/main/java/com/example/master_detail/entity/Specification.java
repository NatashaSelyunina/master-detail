package com.example.master_detail.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Specification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private BigDecimal sum;

    @ManyToOne
    private Document document;

    public Specification() {

    }


    public Specification(Long id, String title, BigDecimal sum) {
        this.id = id;
        this.title = title;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
