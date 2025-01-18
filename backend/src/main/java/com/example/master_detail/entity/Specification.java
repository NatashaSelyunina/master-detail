package com.example.master_detail.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Specification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String title;

    private BigDecimal sum;

    @ManyToOne
    private Document document;

    public Specification(String title, BigDecimal sum) {
        this.title = title;
        this.sum = sum;
    }
}
