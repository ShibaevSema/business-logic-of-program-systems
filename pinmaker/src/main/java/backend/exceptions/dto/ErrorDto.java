package backend.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor(staticName = "of")
public class ErrorDto {
    int code;
    String message;
    LocalDateTime time;
}
