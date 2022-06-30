package uz.jl.mockdata.mockdatagenerator.data;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.mockdata.mockdatagenerator.data.dto.DataCreateDTO;
import uz.jl.mockdata.mockdatagenerator.data.entity.Field;
import uz.jl.mockdata.mockdatagenerator.data.enums.FieldEnum;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;


@Service
@Transactional
@RequiredArgsConstructor
public class DataService {
    private final DataRepository repository;
    private final WriteFileProcessor processor;
    private final Faker faker;

    public String generate(DataCreateDTO dto) {
        String file = createFile(dto.getTableName(), dto.getFileType());
        try (FileWriter fileWriter = new FileWriter(file)) {
            IntStream.range(0, dto.getRowCount()).forEach(i -> {
                try {
                    List<Field> mockDataList = getMockDataList(dto);
                    fileWriter.write(processor.processorType(dto.getFileType(), mockDataList, dto.getTableName(), dto.getRowCount(), i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private List<Field> getMockDataList(DataCreateDTO dto) {
        List<Field> fieldList = new ArrayList<>();
        for (Field field : dto.getFields()) {
            Field field1 = new Field();
            field1.setFieldName(field.getFieldName());
            field1.setFieldType(getMockData(field.getFieldType()));
            fieldList.add(field1);
        }
        return fieldList;
    }

    public String getMockData(String type) {
        FieldEnum fieldEnum = FieldEnum.valueOf(type.toUpperCase(Locale.ROOT));
        return switch (fieldEnum) {
            case ID -> faker.idNumber().valid();
            case UUID -> faker.internet().uuid();
            case FIRST_NAME -> faker.name().firstName();
            case LAST_NAME -> faker.name().lastName();
            case LOREM -> faker.lorem().sentence();
            case ADDRESS -> faker.address().fullAddress();
            case BOOLEAN -> String.valueOf(faker.bool().bool());
            case JOB -> faker.job().title();
            case NAME -> faker.name().name();
            case ZERO_ONE -> Integer.toString(1);
            case DATE_NOW -> LocalDateTime.now().toString();
            case MD5 -> faker.crypto().md5();
            case NUMBER -> String.valueOf(faker.number().numberBetween(0, 1000));
            case TIME -> faker.date().birthday().toString();
            case UNIVERSITY -> faker.university().name();
            default -> throw new IllegalStateException("Unexpected value: " + FieldEnum.valueOf(type));
        };
    }

    public String createFile(String fileName, String type) {
        try {
            String newFile = fileName + "." + type;
            File file = new File("src/main/resources/file/" + newFile);
            file.createNewFile();
            return "src/main/resources/file/" + newFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
