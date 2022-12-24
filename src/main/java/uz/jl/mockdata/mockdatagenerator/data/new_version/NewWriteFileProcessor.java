package uz.jl.mockdata.mockdatagenerator.data.new_version;

import org.springframework.stereotype.Component;
import uz.jl.mockdata.mockdatagenerator.data.enums.DownloadTypeEnum;

import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/22/2022
 * <br/>
 * Time: 12:38 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.data
 * <br/>
 *
 * <p>
 * This class passes fake data to be written to the file as a string and returns a string.
 * In the first step, writing objects is determined by file type.
 * </p>
 *
 * @version 2.0
 * @since 1.4
 */
@Component
public class NewWriteFileProcessor {

    /**
     * This method returns a list of dummy objects based on the type of the incoming
     * DownloadTypeEnum to the corresponding method.
     * This method returns the string returned by the method corresponding to the file type.
     *
     * @param type {@link DownloadTypeEnum} this parameter is included to specify which file type to return a string for.
     * @param list {@link NewFieldValue} this parameter comes in as a list of dummy objects.
     * @param tableName {@link String} this parameter comes in as the table name for the sql type.
     *                                this parameter is only used for sql type.
     * @param rowCount this parameter comes in only for files of type json. rowCount parameter is used for check in writeJSON method.
     * @param count this parameter is used to find out which row of dummy data has arrived.
     * @return This method returns the string returned from the switch.
     */
    public String processorType(DownloadTypeEnum type, List<NewFieldValue> list, String tableName, int rowCount, int count) {
        return switch (type) {
            case CSV -> writeCSV(list, count);
            case SQL -> writeSQL(list, tableName);
            case JSON -> writeJSON(list, rowCount, count);
        };
    }

    /**
     * This method converts list of dummy objects i.e. objects of {@link NewFieldValue} class to sql and returns {@link String}.
     *
     * @param list The NewFieldValue object list comes in.
     * @param tableName The name of the table to be inserted is entered.
     * @return {@link String} - returns a string with dummy data to write to file.
     */
    private String writeSQL(List<NewFieldValue> list, String tableName) {
        StringBuilder sql = new StringBuilder("insert into ").append(tableName).append(" (");
        for (NewFieldValue field : list) {
            sql.append(field.getName()).append(", ");
        }
        StringBuilder s = new StringBuilder(sql.substring(0, sql.length() - 2).concat(")").concat(" values ("));
        for (NewFieldValue field : list) {
            if (field.getName().equalsIgnoreCase("id")) {
                s.append(field.getValue()).append(", ");
            } else {
                s.append("'").append(field.getValue()).append("'").append(", ");
            }
        }
        return s.substring(0, s.length() - 2).concat(");").concat("\n");
    }

    private String writeCSV(List<NewFieldValue> list, int count) {
        if (count == 0) {
            return writeCSVWithStart(list);
        } else {
            return writeCSVNormal(list);
        }
    }

    private String writeCSVWithStart(List<NewFieldValue> list) {
        StringBuilder s = new StringBuilder("");
        for (NewFieldValue field : list) {
            s.append(field.getName()).append(",");
        }
        StringBuilder builder = new StringBuilder(s.substring(0, s.length() - 1).concat("\n"));
        for (NewFieldValue field : list) {
            builder.append(field.getValue()).append(",");
        }
        return builder.substring(0, builder.length() - 1).concat("\n");
    }

    private String writeCSVNormal(List<NewFieldValue> list) {
        StringBuilder builder = new StringBuilder();
        for (NewFieldValue field : list) {
            builder.append(field.getValue()).append(",");
        }
        return builder.substring(0, builder.length() - 1).concat("\n");
    }

    private String writeJSON(List<NewFieldValue> list, int rowCount, int count) {
        if (count == 0) {
            return writeJsonStart(list);
        } else if ((rowCount - 1) == count) {
            return writeJsonEnd(list);
        } else {
            return writeJsonNormal(list);
        }
    }

    private String writeJsonStart(List<NewFieldValue> list) {
        StringBuilder builder = new StringBuilder("[{");
        for (NewFieldValue field : list) {
            builder.append("\"").append(field.getName()).append("\"").append(":\"").append(field.getValue()).append("\",");
        }
        return builder.substring(0, builder.length() - 1).concat("}, \n");
    }

    private String writeJsonNormal(List<NewFieldValue> list) {
        StringBuilder builder = new StringBuilder("{");
        for (NewFieldValue field : list) {
            builder.append("\"").append(field.getName()).append("\"").append(":\"").append(field.getValue()).append("\",");
        }
        return builder.substring(0, builder.length() - 1).concat("}, \n");
    }

    private String writeJsonEnd(List<NewFieldValue> list) {
        StringBuilder builder = new StringBuilder("{");
        for (NewFieldValue field : list) {
            builder.append("\"").append(field.getName()).append("\"").append(":\"").append(field.getValue()).append("\",");
        }
        return builder.substring(0, builder.length() - 1).concat("}]");
    }
}
