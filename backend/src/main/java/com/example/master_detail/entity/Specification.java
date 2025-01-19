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

    public Specification(String title, BigDecimal sum) {
        this.title = title;
        this.sum = sum;
    }
}
