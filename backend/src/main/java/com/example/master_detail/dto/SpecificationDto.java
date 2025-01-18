package com.example.master_detail.dto;

import com.example.master_detail.entity.Specification;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecificationDto {

    @NotNull
    private String title;

    private BigDecimal sum;

    public static SpecificationDto from(Specification specification) {
        return new SpecificationDto(
                specification.getTitle(),
                specification.getSum());
    }

    public static Set<SpecificationDto> from(Set<Specification> specifications) {
        return specifications.stream()
                .map(SpecificationDto::from)
                .collect(Collectors.toSet());
    }

    public static Specification to(SpecificationDto specificationDto) {
        return new Specification(
                specificationDto.title,
                specificationDto.sum);
    }

    public static Set<Specification> to(Set<SpecificationDto> specificationDtos) {
        return specificationDtos.stream()
                .map(SpecificationDto::to)
                .collect(Collectors.toSet());
    }
}
