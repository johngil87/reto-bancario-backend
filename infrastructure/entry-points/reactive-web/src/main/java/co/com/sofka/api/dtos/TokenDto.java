package co.com.sofka.api.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TokenDto {
    private String token;
    private String message;
}
