package uz.jl.mockdata.mockdatagenerator.data;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.jl.mockdata.mockdatagenerator.response.ApplicationError;

import java.time.LocalDateTime;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/19/2023
 * <br/>
 * Time: 4:47 PM
 * <br/>
 * Package: uz.jl.mockdata.mockdatagenerator.data
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApplicationError applicationError = new ApplicationError();
        applicationError.setCode("MethodArgumentNotValidException");
        applicationError.setMessage(ex.getMessage());
        applicationError.setStatus(400);
        applicationError.setTime(LocalDateTime.now());
        return new ResponseEntity<>(applicationError, HttpStatus.BAD_REQUEST);
    }


}
