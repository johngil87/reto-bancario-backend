package co.com.sofka.api;

import co.com.sofka.model.ex.BusinessExceptions;
import co.com.sofka.model.ex.ErrorResponse;
import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import reactor.core.publisher.Mono;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Log
@RestControllerAdvice
public class WebExceptionsConfig {

    @ExceptionHandler(BusinessExceptions.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleBusinessException(BusinessExceptions ex) {
        log.info(LocalDateTime.now() + " " +ex.getMessage());
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .dateTime(LocalDateTime.now()).build();
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    ResponseEntity<Mono<ErrorResponse>> serverNotFoundExceptionHandler() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(Mono.just(ErrorResponse.builder().message("servicio no disponible intente mas tarde").dateTime(LocalDateTime.now()).build()), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({SQLDataException.class, SQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse SQLException(Exception ex) {
        log.info(LocalDateTime.now() + " " +ex.getMessage());
        return ErrorResponse.builder()
                .message("Ha ocurrido un error inesperado: con la base de datos ")
                .dateTime(LocalDateTime.now()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleAllOtherException(Exception ex) {
        log.info(LocalDateTime.now() + " " +ex.getMessage());
        return ErrorResponse.builder()
                .message("Ha ocurrido un error inesperado:"+ ex.getMessage())
                .dateTime(LocalDateTime.now()).build();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleThrowableException(Throwable ex) {
        log.info(LocalDateTime.now() + " " +ex.getMessage());
        return ErrorResponse.builder()
                .message("Ha ocurrido un error inesperado:"+ex.getMessage())
                .dateTime(LocalDateTime.now()).build();
    }
}
