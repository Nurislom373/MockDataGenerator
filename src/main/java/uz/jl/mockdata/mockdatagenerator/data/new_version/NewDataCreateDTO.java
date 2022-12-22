package uz.jl.mockdata.mockdatagenerator.data.new_version;

import lombok.*;
import uz.jl.mockdata.mockdatagenerator.data.enums.DownloadTypeEnum;

import javax.validation.constraints.*;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/22/2022
 * <br/>
 * Time: 2:49 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.data.new_version
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewDataCreateDTO {
    @NotNull
    private List<NewFieldType> fields;
    @NotNull
    private DownloadTypeEnum fileType;
    @NotNull
    @Min(1)
    @Max(1_000_000)
    private int rowCount;
    @NotBlank
    @Size(min = 2, max = 120, message = "tableName must be requires not null")
    private String tableName;
}
