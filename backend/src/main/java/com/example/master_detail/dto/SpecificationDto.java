package com.example.master_detail.dto;

import com.example.master_detail.entity.Specification;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecificationDto {

    private Long id;

    @NotNull
    private String title;

    private BigDecimal sum;

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
}
