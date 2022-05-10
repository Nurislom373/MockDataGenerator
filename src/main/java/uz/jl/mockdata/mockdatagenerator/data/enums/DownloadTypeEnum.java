package uz.jl.mockdata.mockdatagenerator.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DownloadTypeEnum {
    SQL("SQL"),
    JSON("JSON"),
    CSV("CSV"),
    TXT("TXT");

    private final String value;
}
