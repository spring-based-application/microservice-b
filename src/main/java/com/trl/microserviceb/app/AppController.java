package com.trl.microserviceb.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/app")
public class AppController {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post() {
        ResponseEntity<String> response = null;

        response = ResponseEntity.status(HttpStatus.CREATED).body("(microservice-b)(post)");

        return response;
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        response = ResponseEntity.status(HttpStatus.OK).body("(microservice-b)(get)(id=" + id + ")");

        return response;
    }

    @PatchMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> patch(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        response = ResponseEntity.status(HttpStatus.OK).body("(microservice-b)(patch)(id=" + id + ")");

        return response;
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        response = ResponseEntity.status(HttpStatus.OK).body("(microservice-b)(patch)(id=" + id + ")");

        return response;
    }
}
