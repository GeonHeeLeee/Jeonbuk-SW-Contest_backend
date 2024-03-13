package Jeonbuk.contest.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ErrorDTO> handleCustomException(CustomException ex, HttpServletRequest request) {
        log.error("CustomException. URL: {}, ErrorMessage: {}", request.getRequestURL(), ex.getErrorCode().getErrorMessage());
        return ErrorDTO.toResponseEntity(ex);
    }
}
