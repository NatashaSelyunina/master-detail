package com.example.master_detail.dto;

import com.example.master_detail.entity.Specification;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class SpecificationDto {

    @NotNull
    private String title;

    private BigDecimal sum;

    public SpecificationDto(String title, BigDecimal sum) {
        this.title = title;
        this.sum = sum;
    }

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public static SpecificationDto from(Specification specification) {
        return new SpecificationDto(
                specification.getTitle(),
                specification.getSum());
    }

    public static List<SpecificationDto> from(List<Specification> specifications) {
        return specifications.stream()
                .map(SpecificationDto::from)
                .toList();
    }

    public static Specification to(SpecificationDto specificationDto) {
        return new Specification(
                specificationDto.title,
                specificationDto.sum);
    }

    public static List<Specification> to(List<SpecificationDto> specificationDtos) {
        return specificationDtos.stream()
                .map(SpecificationDto::to)
                .toList();
    }
}
