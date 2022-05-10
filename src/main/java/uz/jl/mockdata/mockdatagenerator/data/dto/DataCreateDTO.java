package uz.jl.mockdata.mockdatagenerator.data.dto;

import lombok.*;
import uz.jl.mockdata.mockdatagenerator.data.entity.Field;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DataCreateDTO {
    private List<Field> fields;
    private String fileType;
    private int rowCount;
    private String tableName;
}
