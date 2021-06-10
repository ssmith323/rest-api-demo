package info.stephenjsmith.apidemo.rest;

import org.springframework.stereotype.Component;

@Component
public class ExceptionToResponseBuilder {

    public ExceptionResponse build(String httpStatus, Exception source) {
        return ExceptionResponse.builder()
                .status(httpStatus)
                .message(source.getMessage())
                .build();
    }
}