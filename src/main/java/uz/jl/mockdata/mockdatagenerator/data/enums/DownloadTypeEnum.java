package uz.jl.mockdata.mockdatagenerator.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum DownloadTypeEnum {
    SQL("sql"),
    JSON("json"),
    CSV("csv"),
    TXT("txt");

    private final String value;

    public static String getValue(DownloadTypeEnum typeEnum) {
        return Arrays.stream(values())
                .filter(f -> f.equals(typeEnum)).findFirst()
                .orElseThrow(() -> new RuntimeException("Not found")).getValue();
    }
}
