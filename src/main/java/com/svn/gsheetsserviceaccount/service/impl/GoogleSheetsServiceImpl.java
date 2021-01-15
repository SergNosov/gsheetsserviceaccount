package com.svn.gsheetsserviceaccount.service.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.svn.gsheetsserviceaccount.Global;
import com.svn.gsheetsserviceaccount.service.GoogleConnectionService;
import com.svn.gsheetsserviceaccount.service.GoogleSheetsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.sheets.v4.model.ValueRange;

@Service
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private final String appName;
    private final String spreadsheetId;
    private final String sheetName;
    private final GoogleConnectionService googleConnectionService;
    private Sheets sheets = null;

    public GoogleSheetsServiceImpl(@Value("${google.app.name}")
                                           String appName,
                                   @Value("${google.spreadsheet.id}")
                                           String spreadsheetId,
                                   @Value("${google.spreadsheet.sheet.name}")
                                           String sheetName,
                                   GoogleConnectionService googleConnectionService) {
        this.appName = appName;
        this.spreadsheetId = spreadsheetId;
        this.sheetName = sheetName;
        this.googleConnectionService = googleConnectionService;
    }

    @Override
    public List<List<String>> readTable() throws IOException {
        Sheets service = getSheetsService();
        return readSheets(service, spreadsheetId, sheetName);
    }

    private Sheets getSheetsService() throws IOException {
        if (this.sheets == null) {
            this.sheets = new Sheets.Builder(
                    Global.HTTP_TRANSPORT,
                    Global.JSON_FACTORY,
                    googleConnectionService.getCredentials())
                    .setApplicationName(appName).build();
        }
        return this.sheets;
    }

    private List<List<String>> readSheets(Sheets service, String spreadsheetId, String sheetName) throws IOException {
        ValueRange table = service.spreadsheets().values().get(spreadsheetId, sheetName).execute();

        List<List<String>> stringValues = valuesToString(table.getValues());
        printTable(stringValues);

        return stringValues;
    }

    private List<List<String>> valuesToString(List<List<Object>> objectValues){
        List<List<String>> stringValues = new ArrayList<>();

        for (List<Object> objectsRow: objectValues){
            stringValues.add(Lists.transform(objectsRow, Functions.toStringFunction()));
        }
        return stringValues;
    }

    private void printTable(List<List<String>> stringValues) {
        if (stringValues == null || stringValues.size() == 0) {
            System.err.println("No data found.");
        } else {
            System.out.println("Data from google sheets:");
            stringValues.stream().forEach(System.out::println);
        }
    }
}
