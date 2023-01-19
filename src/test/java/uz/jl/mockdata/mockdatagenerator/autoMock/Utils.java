package uz.jl.mockdata.mockdatagenerator.autoMock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

public abstract class Utils {

    public static final String PATH = "/api/v1";
    public static final String DETAIL = "/detail";
    public static final String GET = "/get";
    public static final String LIST = "/list";
    public static final String CREATE = "/create";
    public static final String DELETE = "/delete";

    public static LinkedMultiValueMap<String, String> MAP = new LinkedMultiValueMap<>();

    static {
        MAP.add("size", "20");
        MAP.add("page", "0");
        MAP.add("direction", "ASC");
    }

    public static List<ResultMatcher> matchers(StatusChoice choice) {
        return switch (choice) {
            case OK -> List.of(MockMvcResultMatchers.status().isOk(),
                    MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
            case CREATED -> List.of(MockMvcResultMatchers.status().isCreated(),
                    MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
            case NO_CONTENT -> List.of(MockMvcResultMatchers.status().isNoContent(),
                    MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
            case NOT_ACCEPTABLE -> List.of(MockMvcResultMatchers.status().isNotAcceptable(),
                    MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
            case BAD_REQUEST -> List.of(MockMvcResultMatchers.status().isBadRequest(),
                    MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
            case NOT_FOUND -> List.of(MockMvcResultMatchers.status().isNotFound(),
                    MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        };
    }

    @Getter
    @RequiredArgsConstructor
    public enum ControllerExt {
        AUTH_BLOCK("/auth_block");
        private final String value;
    }

    public enum StatusChoice {
        OK,
        CREATED,
        NO_CONTENT,
        NOT_ACCEPTABLE,
        BAD_REQUEST,
        NOT_FOUND;
    }

}
