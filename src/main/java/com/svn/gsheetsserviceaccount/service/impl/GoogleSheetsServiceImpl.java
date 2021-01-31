package com.svn.gsheetsserviceaccount.service.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.svn.gsheetsserviceaccount.service.GoogleSheetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.sheets.v4.model.ValueRange;

@Slf4j
@Service
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private final String spreadsheetId;
    private final String sheetName;
    private final Sheets sheets;

    public GoogleSheetsServiceImpl(@Value("${google.spreadsheet.id}")
                                           String spreadsheetId,
                                   @Value("${google.spreadsheet.sheet.name}")
                                           String sheetName,
                                   Sheets googleSheets) {
        this.spreadsheetId = spreadsheetId;
        this.sheetName = sheetName;
        this.sheets = googleSheets;
    }

    @Override
    public List<List<String>> readTable() {
        try {
            ValueRange table = sheets.spreadsheets().values().get(spreadsheetId, sheetName).execute();

            List<List<String>> stringValues = valuesToString(table.getValues());
            printTable(stringValues);

            return List.copyOf(stringValues);
        } catch (IOException ioe) {
            log.error("--- Не удалось получить данные из источника данных. Ошибка: " + ioe.getMessage());
            throw new RuntimeException("--- Не удалось получить данные из источника данных.", ioe);
        }
    }

    private List<List<String>> valuesToString(List<List<Object>> objectValues) {
        List<List<String>> stringValues = new ArrayList<>();

        for (List<Object> objectsRow : objectValues) {
            stringValues.add(Lists.transform(objectsRow, Functions.toStringFunction()));
        }
        return stringValues;
    }

    private void printTable(List<List<String>> stringValues) {
        if (stringValues == null || stringValues.size() == 0) {
            log.warn("--- No data found.");
        } else {
            stringValues.stream().forEach((s)->log.info("--- string from google sheets: "+s));
        }
    }
}