package backend.exceptions;

import backend.exceptions.dto.ErrorDto;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final ErrorDto error;

    public ApplicationException(ErrorDto error) {
        super(error.getMessage());
        this.error = error;
    }

    public ErrorDto getError() {
        return this.error;
    }
}
