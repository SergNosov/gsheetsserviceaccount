package com.svn.gsheetsserviceaccount.service.impl;

import com.svn.gsheetsserviceaccount.service.ContactService;
import com.svn.gsheetsserviceaccount.service.DataTransferService;
import com.svn.gsheetsserviceaccount.service.GoogleSheetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataTransferServiceImpl implements DataTransferService {

    private final GoogleSheetsService googleSheetsService;
    private final ContactService contactService;

    @Override
    public void transfer() {
       // List<List<String>> googleTableValues = googleSheetsService.readTable()
    }
}
