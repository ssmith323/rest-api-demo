package info.stephenjsmith.apidemo.rest;

import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface IRestController<T, S> {
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    List<T> getAll();

    @ResponseStatus(CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    T create(@RequestBody T t);

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    T getById(@PathVariable S id);

    @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    T updateById(@PathVariable S id, @RequestBody T t);

    @ResponseStatus(ACCEPTED)
    @DeleteMapping(path = "/{id}")
    void deleteById(@PathVariable S id);
}
