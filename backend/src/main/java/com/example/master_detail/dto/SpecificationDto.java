package com.example.master_detail.dto;

import com.example.master_detail.entity.Specification;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class SpecificationDto {

    private Long id;

    @NotBlank
    private String title;

    @NotNull
    @Min(value = 0)
    private BigDecimal sum;

    public SpecificationDto(Long id, String title, BigDecimal sum) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static SpecificationDto from(Specification specification) {
        return new SpecificationDto(
                specification.getId(),
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
                specificationDto.getId(),
                specificationDto.title,
                specificationDto.sum);
    }

    public static List<Specification> to(List<SpecificationDto> specificationDtos) {
        return specificationDtos.stream()
                .map(SpecificationDto::to)
                .toList();
    }
}
