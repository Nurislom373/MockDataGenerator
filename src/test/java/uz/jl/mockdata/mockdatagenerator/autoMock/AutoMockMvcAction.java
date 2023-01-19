package uz.jl.mockdata.mockdatagenerator.autoMock;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ExceptionCollector;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

@Service
public class AutoMockMvcAction implements AutoMockMvc {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void obsessiveGet(String path, LinkedMultiValueMap<String, String> multiValueMap, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path).params(multiValueMap))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessiveGet(String path, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessiveGet(String path, Object variable, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(path, variable))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessiveDelete(String path, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(path))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessiveDelete(String path, Object variable, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(path, variable))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessiveDeleteObj(String path, Object value, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessivePost(String path, Object value, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    @Override
    public void obsessivePut(String path, Object value, List<ResultMatcher> matchers, ResultHandler handler) throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(objectMapper.writeValueAsString(value)))
                .andDo(handler);
        matchersAction(resultActions, matchers);
    }

    private void matchersAction(ResultActions actions, List<ResultMatcher> matchers) throws Exception {
        ExceptionCollector exceptionCollector = new ExceptionCollector();
        matchers.forEach(match -> {
            exceptionCollector.execute(() -> actions.andExpect(match));
        });
        exceptionCollector.assertEmpty();
    }


}
