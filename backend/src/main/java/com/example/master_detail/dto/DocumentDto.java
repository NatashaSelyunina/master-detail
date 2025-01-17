package com.example.master_detail.dto;

import com.example.master_detail.entity.Document;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentDto {

    private Long id;

    @NotNull
    private String number;

    @NotNull
    private LocalDate date;

    private BigDecimal sum;

    private String note;

    private List<SpecificationDto> specifications;

    public static DocumentDto from(Document document) {
        List<SpecificationDto> specifications = SpecificationDto.from(document.getSpecifications());
        return new DocumentDto(
                document.getId(),
                document.getNumber(),
                document.getDate(),
                document.getSum(),
                document.getNote(),
                specifications);
    }
}
