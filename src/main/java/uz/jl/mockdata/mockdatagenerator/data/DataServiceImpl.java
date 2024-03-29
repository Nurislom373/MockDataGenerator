package uz.jl.mockdata.mockdatagenerator.data;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileUrlResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import uz.jl.mockdata.mockdatagenerator.data.dto.DataCreateDTO;
import uz.jl.mockdata.mockdatagenerator.data.entity.DataEntity;
import uz.jl.mockdata.mockdatagenerator.data.dto.Field;
import uz.jl.mockdata.mockdatagenerator.data.enums.DownloadTypeEnum;
import uz.jl.mockdata.mockdatagenerator.data.enums.MockType;
import uz.jl.mockdata.mockdatagenerator.data.exceptions.InternalServerException;
import uz.jl.mockdata.mockdatagenerator.data.exceptions.NotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/11/2022
 * <br/>
 * Time: 11:01 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.data
 */
@Service
@RequiredArgsConstructor
@EnableScheduling
public class DataServiceImpl implements DataService {

    private final DataRepository repository;
    private final WriteFileProcessor processor;
    private final Faker faker;

    @Deprecated
    @Override
    public UUID generate(DataCreateDTO dto) {
        checkData(dto);
        String file = createFile(dto.getTableName(), DownloadTypeEnum.getValue(dto.getFileType()));
        try (FileWriter fileWriter = new FileWriter(file)) {
            IntStream.rangeClosed(0, dto.getRowCount()).forEach(i -> {
                try {
                    List<Field> mockDataList = getMockDataList(dto, i);
                    fileWriter.write(processor.processorType(dto.getFileType(), mockDataList,
                            dto.getTableName(), dto.getRowCount(), i));
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
    public void get(HttpServletResponse response, UUID id) {
        DataEntity dataEntity = repository.findByCode(id)
                .orElseThrow(() -> new NotFoundException("Your Table data is not found!"));
        try {
            dataEntity.setGet(true);
            repository.save(dataEntity);
            internGet(response, new FileUrlResource(dataEntity.getPath()).getFile());
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerException();
        }
    }

    private void internGet(HttpServletResponse response, File file) throws IOException {
        if (file.exists()) {
            String name = URLConnection.guessContentTypeFromName(file.getName());
            if (name == null) {
                name = "application/octet-stream";
            }
            response.setContentType(name);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
            response.setContentLength((int) file.length());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(bufferedInputStream, response.getOutputStream());
        }
    }

    @Override
//    @Scheduled(fixedDelay = 120000)
    public void delete() {
        List<DataEntity> list = repository.findAll()
                .stream()
                .filter(DataEntity::isGet)
                .toList();
        if (!list.isEmpty()) {
            list.forEach((data) -> {
                boolean delete = new File(data.getPath()).delete();
                if (delete) {
                    System.out.println("Successfully Deleted File!");
                }
            });
            repository.deleteAll(list);
        }
    }

    private void checkData(DataCreateDTO dto) {
        if (dto.getRowCount() < 0 || dto.getRowCount() > 1e7) {
            throw new RuntimeException("Invalid Row Count!");
        }
    }

    @Deprecated
    private UUID save(DataCreateDTO dto) {
        DataEntity data = new DataEntity();
        data.setCount(dto.getRowCount());
        data.setFileType(DownloadTypeEnum.getValue(dto.getFileType()));
        data.setTableName(dto.getTableName());
        data.setPath("src/main/resources/file/" + dto.getTableName() + "." + DownloadTypeEnum.getValue(dto.getFileType()));
        return repository.save(data).getCode();
    }

    private List<Field> getMockDataList(DataCreateDTO dto, Integer id) {
        List<Field> fieldList = new ArrayList<>();
        for (Field field : dto.getFields()) {
            Field field1 = new Field();
            field1.setFieldName(field.getFieldName());
            field1.setFieldType(getMockData(field.getFieldType(), id));
            fieldList.add(field1);
        }
        return fieldList;
    }

    public String getMockData(String type, Integer id) {
        return switch (MockType.valueOf(type.toUpperCase(Locale.ROOT))) {
            case ID -> String.valueOf(id + 1);
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
            case GENDER -> faker.book().title();
            default -> throw new IllegalStateException("Unexpected value: " + MockType.valueOf(type));
        };
    }

    public String createFile(String fileName, String type) {
        try {
            String newFile = fileName + "." + type;
            File file = new File("src/main/resources/file/" + newFile);
            file.createNewFile();
            return "src/main/resources/file/" + newFile;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
