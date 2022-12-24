package uz.jl.mockdata.mockdatagenerator.data.new_version;

import com.github.javafaker.Faker;
import uz.jl.mockdata.mockdatagenerator.data.enums.MockType;

import java.time.LocalDateTime;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/22/2022
 * <br/>
 * Time: 12:31 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.data
 * <br/>
 *
 * <p>
 * This class implements MockService and CreateMockData services.
 * The main purpose of this abstract class is to generate mock data through the Faker class.
 * AbstractMockService class has only one method and this method returns data on the mock type enum entered.
 * </p>
 *
 * @version 2.0
 * @see CreateMocData
 * @see MockService
 * @since 1.0
 */
public abstract class AbstractMockService implements MockService, CreateMocData {

    private final Faker faker;

    protected AbstractMockService(Faker faker) {
        this.faker = faker;
    }

    @Override
    public final String getData(MockType type, Integer id) {
        return switch (type) {
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
            case USERNAME -> faker.name().username();
            case EMAIL -> faker.internet().emailAddress();
            case DATE_NOW -> LocalDateTime.now().toString();
            case MD5 -> faker.crypto().md5();
            case NUMBER -> String.valueOf(faker.number().numberBetween(0, 1000));
            case TIME -> faker.date().birthday().toString();
            case UNIVERSITY -> faker.university().name();
            case GENDER -> faker.book().title();
            case PASSWORD -> faker.crypto().sha256();
            case SENTENCES -> faker.lorem().sentence();
            case WORDS -> faker.lorem().word();
            case WEATHER -> faker.weather().description();
            case TEAM -> faker.team().name();
            case PHONE_NUMBER -> faker.phoneNumber().phoneNumber();
            case ZERO_ONE -> String.valueOf(faker.number().numberBetween(0, 1));
        };
    }

}
