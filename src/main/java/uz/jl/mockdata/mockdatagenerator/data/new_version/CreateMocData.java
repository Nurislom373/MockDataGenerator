package uz.jl.mockdata.mockdatagenerator.data.new_version;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/22/2022
 * <br/>
 * Time: 2:42 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.data.new_version
 */
public interface CreateMocData {

    List<NewFieldValue> getMockDataList(NewDataCreateDTO dto, Integer id);

}
