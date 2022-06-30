package uz.jl.mockdata.mockdatagenerator.data;

import org.springframework.stereotype.Component;
import uz.jl.mockdata.mockdatagenerator.data.entity.Field;
import uz.jl.mockdata.mockdatagenerator.data.enums.DownloadTypeEnum;

import java.util.List;
import java.util.Locale;

@Component
public class WriteFileProcessor {

    public String processorType(String type, List<Field> list, String tableName, int rowCount, int count) {
        return switch (DownloadTypeEnum.valueOf(type.toUpperCase(Locale.ROOT))) {
            case CSV -> writeCSV(list, count);
            case SQL -> writeSQL(list, tableName);
            case JSON -> writeJSON(list, rowCount, count);
            default -> throw new IllegalStateException("Unexpected value: " + DownloadTypeEnum.valueOf(type.toUpperCase(Locale.ROOT)));
        };
    }

    private String writeSQL(List<Field> list, String tableName) {
        StringBuilder sql = new StringBuilder("insert into ").append(tableName).append(" (");
        for (Field field : list) {
            sql.append(field.getFieldName()).append(", ");
        }
        StringBuilder s = new StringBuilder(sql.substring(0, sql.length() - 2).concat(")").concat(" values ("));
        for (Field field : list) {
            if (field.getFieldName().equalsIgnoreCase("id")) {
                s.append(field.getFieldType()).append(", ");
            } else {
                s.append("'").append(field.getFieldType()).append("'").append(", ");
            }
        }
        return s.substring(0, s.length() - 2).concat(");").concat("\n");
    }

    private String writeCSV(List<Field> list, int count) {
        if (count == 0) {
            return writeCSVWithStart(list);
        } else {
            return writeCSVNormal(list);
        }
    }

    private String writeCSVWithStart(List<Field> list) {
        StringBuilder s = new StringBuilder("");
        for (Field field : list) {
            s.append(field.getFieldName()).append(",");
        }
        StringBuilder builder = new StringBuilder(s.substring(0, s.length() - 1).concat("\n"));
        for (Field field : list) {
            builder.append(field.getFieldType()).append(",");
        }
        return builder.substring(0, builder.length() - 1).concat("\n");
    }

    private String writeCSVNormal(List<Field> list) {
        StringBuilder builder = new StringBuilder();
        for (Field field : list) {
            builder.append(field.getFieldType()).append(",");
        }
        return builder.substring(0, builder.length() - 1).concat("\n");
    }

    private String writeJSON(List<Field> list, int rowCount, int count) {
        if (count == 0) {
            return writeJsonStart(list);
        } else if ((rowCount - 1) == count) {
            return writeJsonEnd(list);
        } else {
            return writeJsonNormal(list);
        }
    }

    private String writeJsonStart(List<Field> list) {
        StringBuilder builder = new StringBuilder("[{");
        for (Field field : list) {
            builder.append("\"").append(field.getFieldName()).append("\"").append(":\"").append(field.getFieldType()).append("\",");
        }
        return builder.substring(0, builder.length() - 1).concat("}, \n");
    }

    private String writeJsonNormal(List<Field> list) {
        StringBuilder builder = new StringBuilder("{");
        for (Field field : list) {
            builder.append("\"").append(field.getFieldName()).append("\"").append(":\"").append(field.getFieldType()).append("\",");
        }
        return builder.substring(0, builder.length() - 1).concat("}, \n");
    }

    private String writeJsonEnd(List<Field> list) {
        StringBuilder builder = new StringBuilder("{");
        for (Field field : list) {
            builder.append("\"").append(field.getFieldName()).append("\"").append(":\"").append(field.getFieldType()).append("\",");
        }
        return builder.substring(0, builder.length() - 1).concat("}]");
    }
}
