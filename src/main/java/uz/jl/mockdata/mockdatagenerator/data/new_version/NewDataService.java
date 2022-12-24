package uz.jl.mockdata.mockdatagenerator.data.new_version;

import org.springframework.stereotype.Service;
import uz.jl.mockdata.mockdatagenerator.data.DataRepository;
import uz.jl.mockdata.mockdatagenerator.data.entity.DataEntity;
import uz.jl.mockdata.mockdatagenerator.data.enums.DownloadTypeEnum;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/24/2022
 * <br/>
 * Time: 9:54 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.data.new_version
 */
@Service
public class NewDataService {

    private final CreateMocData mocData;
    private final DataRepository repository;
    private final NewWriteFileProcessor processor;

    public NewDataService(CreateMocData mocData, DataRepository repository, NewWriteFileProcessor processor) {
        this.mocData = mocData;
        this.repository = repository;
        this.processor = processor;
    }

    public UUID generate(NewDataCreateDTO dto) {
        String file = createFile(dto.getTableName(), DownloadTypeEnum.getValue(dto.getFileType()));
        try (FileWriter fileWriter = new FileWriter(file)) {
            IntStream.range(0, dto.getRowCount()).forEach(i -> {
                try {
                    List<NewFieldValue> list = mocData.getMockDataList(dto, i);
                    fileWriter.write(processor.processorType(dto.getFileType(), list,
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

    private UUID save(NewDataCreateDTO dto) {
        DataEntity data = new DataEntity();
        data.setCount(dto.getRowCount());
        data.setFileType(DownloadTypeEnum.getValue(dto.getFileType()));
        data.setTableName(dto.getTableName());
        data.setPath("src/main/resources/file/" + dto.getTableName() + "." + DownloadTypeEnum.getValue(dto.getFileType()));
        return repository.save(data).getCode();
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
