package uz.jl.mockdata.mockdatagenerator.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FieldCategoryEnum {
    USER(1, "User", "This field category is very good"),
    NUMBER(2, "Number", "This field category is good"),
    TEXT(3, "Text", "This field category is good"),
    ANIMAL(4, "Animal", "This field category is very bad"),
    TIME(5, "Time", "This field category is very bad"),
    WEATHER(6, "Weather", "This field category is very bad");

    private final Integer id;
    private final String title;
    private final String description;
}
