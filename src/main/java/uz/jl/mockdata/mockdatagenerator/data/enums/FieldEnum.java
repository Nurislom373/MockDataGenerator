package uz.jl.mockdata.mockdatagenerator.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FieldEnum {
    ID(2, "ID", ""),
    NAME(1, "Name", ""),
    FIRST_NAME(1, "First Name", ""),
    LAST_NAME(1, "Last Name", ""),
    USERNAME(1, "Username", ""),
    EMAIL(1, "Email", ""),
    PASSWORD(1, "Password", ""),
    ADDRESS(1, "Address", ""),
    GENDER(1, "Gender", ""),
    NUMBER(2, "Number", ""),
    SENTENCES(3, "Sentences", ""),
    WORDS(3, "Words", ""),
    TIME(5, "Time", ""),
    WEATHER(6, "Weather", ""),
    JOB(3, "Job", ""),
    UNIVERSITY(3, "University", ""),
    TEAM(3, "Team", ""),
    PHONE_NUMBER(1, "Phone Number", ""),
    BOOLEAN(1, "BOOLEAN", ""),
    LOREM(3, "Lorem", ""),
    UUID(1, "UUID", ""),
    DATE_NOW(1, "DATE_NOW", ""),
    ZERO_ONE(1, "ZERO_ONE", ""),
    MD5(1, "MD5", "");

    private final Integer categoryId;
    private final String title;
    private final String description;
}
