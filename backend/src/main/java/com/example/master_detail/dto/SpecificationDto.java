package com.example.master_detail.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecificationDto {

    private Long id;

    @NotNull
    private String title;

    private BigDecimal sum;
}
