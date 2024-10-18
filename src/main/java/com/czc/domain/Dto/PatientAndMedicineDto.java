package com.czc.domain.Dto;

import lombok.Data;

import java.util.List;

@Data
public class PatientAndMedicineDto {

    private Integer pId;

    private String pName;

    private List<MedicineAndPatientDto> medicines;
}
