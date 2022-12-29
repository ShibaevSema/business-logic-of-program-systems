package backend.controllers.controllerAdvice;

import backend.exceptions.ApplicationException;
import backend.exceptions.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GodAdvice {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDto> handleException(ApplicationException e) {
        return new ResponseEntity<>(e.getError(), HttpStatus.valueOf(e.getError().getCode()));
    }

}
