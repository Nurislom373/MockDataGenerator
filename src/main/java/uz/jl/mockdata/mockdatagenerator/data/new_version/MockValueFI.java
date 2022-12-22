package uz.jl.mockdata.mockdatagenerator.data.new_version;

import uz.jl.mockdata.mockdatagenerator.data.enums.MockType;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/22/2022
 * <br/>
 * Time: 12:41 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.data
 */
@FunctionalInterface
public interface MockValueFI {

    String getMockValue(MockType type, Integer id);

}
