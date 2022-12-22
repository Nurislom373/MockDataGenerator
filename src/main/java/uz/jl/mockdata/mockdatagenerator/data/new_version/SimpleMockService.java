package uz.jl.mockdata.mockdatagenerator.data.new_version;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import uz.jl.mockdata.mockdatagenerator.data.enums.MockType;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/22/2022
 * <br/>
 * Time: 12:38 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.data
 *
 * @version 2.0
 * @see AbstractMockService
 * @see MockValueFI
 * @since 1.0
 */
@Service
public class SimpleMockService extends AbstractMockService implements MockValueFI {

    protected SimpleMockService(Faker faker) {
        super(faker);
    }

    @Override
    public String getMockValue(MockType type, Integer id) {
        return super.getData(type, id);
    }

    @Override
    public List<NewFieldValue> getMockDataList(NewDataCreateDTO dto, Integer id) {
        return dto.getFields().stream()
                .map((field) -> genNewField(field, id))
                .toList();
    }

    private NewFieldValue genNewField(NewFieldType field, Integer id) {
        return new NewFieldValue(field.getName(),
                getMockValue(field.getType(), id));
    }
}
