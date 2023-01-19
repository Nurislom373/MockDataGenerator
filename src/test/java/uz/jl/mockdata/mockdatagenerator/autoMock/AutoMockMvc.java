package uz.jl.mockdata.mockdatagenerator.autoMock;

import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

public interface AutoMockMvc {

    void obsessiveGet(String path, LinkedMultiValueMap<String, String> multiValueMap, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveGet(String path, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveGet(String path, Object variable,  List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveDelete(String path, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveDelete(String path, Object variable, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessiveDeleteObj(String path, Object value, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessivePost(String path, Object value, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

    void obsessivePut(String path, Object value, List<ResultMatcher> matchers, ResultHandler handler) throws Exception;

}
