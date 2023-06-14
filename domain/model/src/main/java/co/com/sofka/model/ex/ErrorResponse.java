package co.com.sofka.model.ex;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class ErrorResponse {
    private String message;
    private LocalDateTime dateTime;
}
