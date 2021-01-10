package com.svn.gsheetsserviceaccount.service;

import com.google.api.services.sheets.v4.Sheets;
import com.svn.gsheetsoauth2.Global;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import com.google.api.services.sheets.v4.model.ValueRange;

@Service
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private final String appName;
    private final String spreadsheetId;
    private final String sheetName;
    private Sheets sheets = null;

    public GoogleSheetsServiceImpl(@Value("${google.app.name}")
                                           String appName,
                                   @Value("${google.spreadsheet.id}")
                                           String spreadsheetId,
                                   @Value("${google.spreadsheet.sheet.name}")
                                   String sheetName) {
        this.appName = appName;
        this.spreadsheetId = spreadsheetId;
        this.sheetName = sheetName;
    }

    @Override
    public List<List<Object>> readTable(GoogleConnectionService connection) throws IOException {
        final Sheets service = getSheetsService(connection);
        return readTable(service, spreadsheetId, sheetName);
    }

    private Sheets getSheetsService(GoogleConnectionService gc) throws IOException {
        if (this.sheets == null) {
            this.sheets = new Sheets.Builder(Global.HTTP_TRANSPORT, Global.JSON_FACTORY, gc.getCredentials())
                    .setApplicationName(appName).build();
        }
        return this.sheets;
    }

    private List<List<Object>> readTable(Sheets service, String spreadsheetId, String sheetName) throws IOException {
        ValueRange table = service.spreadsheets().values().get(spreadsheetId, sheetName).execute();

        List<List<Object>> values = table.getValues();
        printTable(values);

        return values;
    }

    private void printTable(List<List<Object>> values) {
        if (values == null || values.size() == 0) {
            System.out.println("No data found.");
        } else {
            System.out.println("read data");
            for (List<Object> row : values) {
                for (int c = 0; c < row.size(); c++) {
                    System.out.printf("%s ", row.get(c));
                }
                System.out.println();
            }
        }
    }
}
