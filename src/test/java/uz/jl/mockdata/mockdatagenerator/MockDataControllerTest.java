package uz.jl.mockdata.mockdatagenerator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import uz.jl.mockdata.mockdatagenerator.autoMock.AutoMockMvc;
import uz.jl.mockdata.mockdatagenerator.autoMock.Utils;
import uz.jl.mockdata.mockdatagenerator.data.enums.DownloadTypeEnum;
import uz.jl.mockdata.mockdatagenerator.data.enums.MockType;
import uz.jl.mockdata.mockdatagenerator.data.new_version.NewDataCreateDTO;
import uz.jl.mockdata.mockdatagenerator.data.new_version.NewFieldType;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/19/2023
 * <br/>
 * Time: 4:30 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class MockDataControllerTest {

    @Autowired
    private AutoMockMvc autoMockMvc;

    @Test
    public void newGenerate() throws Exception {
        var fields = List.of(
                new NewFieldType("id", MockType.ID),
                new NewFieldType("name", MockType.NAME),
                new NewFieldType("email", MockType.EMAIL)
        );

        var body = new NewDataCreateDTO(fields, DownloadTypeEnum.JSON, 1000, "boom");

        autoMockMvc.obsessivePost("/data/new_generate", body,
                Utils.matchers(Utils.StatusChoice.CREATED), MockMvcResultHandlers.print());
    }

    @Test
    public void getFile() throws Exception {
        var id = "6c18b5ff-cf21-4b6b-a3b7-4490bb461b90";

        autoMockMvc.obsessiveGet("/date/get/{id}", id,
                Utils.matchers(Utils.StatusChoice.OK), MockMvcResultHandlers.print());
    }

}
