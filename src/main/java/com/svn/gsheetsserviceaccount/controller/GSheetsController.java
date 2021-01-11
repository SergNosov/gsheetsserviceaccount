package com.svn.gsheetsserviceaccount.controller;

import com.svn.gsheetsserviceaccount.service.GoogleConnectionService;
import com.svn.gsheetsserviceaccount.service.GoogleSheetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
public class GSheetsController {

    private final GoogleConnectionService connectionService;
    private final GoogleSheetsService sheetsService;

    @Autowired
    public GSheetsController(GoogleConnectionService connectionService,
                             GoogleSheetsService sheetsService) {
        this.connectionService = connectionService;
        this.sheetsService = sheetsService;
    }

    @GetMapping({"/","/api/sheet"})
    public ResponseEntity<List<List<Object>>> readGoogleSheet(HttpServletResponse response) throws IOException {

        List<List<Object>> responseBody = sheetsService.readTable(connectionService);

        return new ResponseEntity<List<List<Object>>>(responseBody, HttpStatus.OK);
    }
}
