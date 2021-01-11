package com.svn.gsheetsserviceaccount.aop;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllException(Exception ex, Model model) {
        log.info("--- handleAllException:");
        logginException(ex);

        final StringBuilder message = new StringBuilder("--- ")
                .append(ex.getMessage())
                .append(";");

        return new ResponseEntity<String>(message.toString(), HttpStatus.BAD_REQUEST);
    }

    private void logginException(Exception ex) {
        log.error("--- exception: " + ex.getMessage());
        Arrays.stream(ex.getStackTrace()).forEach(el -> log.error("--- stackTrace: " + el));
    }
}
