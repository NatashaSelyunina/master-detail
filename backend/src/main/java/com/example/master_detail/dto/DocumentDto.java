package com.example.master_detail.dto;

import com.example.master_detail.entity.Document;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DocumentDto {

    private Long id;

    @NotBlank
    private String number;

    @NotNull
    private LocalDate date;

    @Min(value = 0)
    private BigDecimal sum;

    private String note;

    private List<SpecificationDto> specifications;

    public DocumentDto(Long id, String number, LocalDate date, BigDecimal sum, String note,
                       List<SpecificationDto> specifications) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.sum = sum;
        this.note = note;
        this.specifications = specifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getNumber() {
        return number;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public String getNote() {
        return note;
    }

    public List<SpecificationDto> getSpecifications() {
        return specifications;
    }

    public void setNumber(@NotNull String number) {
        this.number = number;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSpecifications(List<SpecificationDto> specifications) {
        this.specifications = specifications;
    }

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

    public static List<DocumentDto> from(List<Document> documents) {
        return documents.stream()
                .map(DocumentDto::from)
                .toList();
    }
}
