package uz.jl.mockdata.mockdatagenerator.data;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import uz.jl.mockdata.mockdatagenerator.data.dto.DataCreateDTO;
import uz.jl.mockdata.mockdatagenerator.data.entity.DataEntity;
import uz.jl.mockdata.mockdatagenerator.data.entity.Field;
import uz.jl.mockdata.mockdatagenerator.data.enums.FieldEnum;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final DataRepository repository;
    private final WriteFileProcessor processor;
    private final Faker faker;

    @Override
    public UUID generate(DataCreateDTO dto) {
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
        return save(dto);
    }

    @Override
    public Resource get(UUID id) {
        try {
            return new FileUrlResource(repository.findByCode(id).orElseThrow(() -> new NotFoundException("Your Table data is not found!")).getPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String delete(UUID id) {
        DataEntity data = repository.findByCode(id).orElseThrow(() -> new NotFoundException("Table data not found"));
        File file = new File(data.getPath());
        if (file.delete()) {
            repository.deleteByCode(id);
            return "Successfully deleted file";
        } else {
            return "Failed to delete the file";
        }
    }

    private UUID save(DataCreateDTO dto) {
        DataEntity data = new DataEntity();
        data.setCount(dto.getRowCount());
        data.setFileType(dto.getFileType());
        data.setTableName(dto.getTableName());
        data.setPath("src/main/resources/file/" + dto.getTableName() + "." + dto.getFileType());
        return repository.save(data).getCode();
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
        return switch (FieldEnum.valueOf(type.toUpperCase(Locale.ROOT))) {
            case UUID -> faker.internet().uuid();
            case FIRST_NAME -> faker.name().firstName();
            case LAST_NAME -> faker.name().lastName();
            case LOREM -> faker.lorem().sentence();
            case ADDRESS -> faker.address().fullAddress();
            case IPV4ADDRESS -> faker.internet().ipV4Address();
            case BOOLEAN -> String.valueOf(faker.bool().bool());
            case JOB -> faker.job().title();
            case NAME -> faker.name().name();
            case EMAIL -> faker.internet().emailAddress();
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
