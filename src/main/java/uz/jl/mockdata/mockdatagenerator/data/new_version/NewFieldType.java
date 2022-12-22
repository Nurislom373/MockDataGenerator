package uz.jl.mockdata.mockdatagenerator.data.new_version;

import lombok.*;
import uz.jl.mockdata.mockdatagenerator.data.enums.MockType;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/22/2022
 * <br/>
 * Time: 2:46 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.data.new_version
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewFieldType {
    private String name;
    private MockType type;
}
