package com.svn.gsheetsserviceaccount.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.svn.gsheetsserviceaccount.service.impl.GoogleSheetsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class GoogleSheetsServiceImpTests {

    @Mock
    private Sheets googleSheets;

    @InjectMocks
    private GoogleSheetsServiceImpl googleSheetsService;

    private Sheets.Spreadsheets spreadsheets = Mockito.mock(Sheets.Spreadsheets.class);
    private Sheets.Spreadsheets.Values values = Mockito.mock(Sheets.Spreadsheets.Values.class);
    private Sheets.Spreadsheets.Values.Get get = Mockito.mock(Sheets.Spreadsheets.Values.Get.class);
    private ValueRange testValueRange = new ValueRange();

    @Test
    @DisplayName("1. Testing table reading. Bad. SpreadsheetId is null.")
    @Order(1)
    void readTableTestNullSpreadsheetIdNull() {
        ReflectionTestUtils.setField(googleSheetsService, "spreadsheetId", null);

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> googleSheetsService.readTable()
        );

        assertEquals("Не верно указаны параметры Google Sheets(spreadsheetId).", iae.getMessage());
    }

    @Test
    @DisplayName("2. Testing table reading. Bad. SheetName is null.")
    @Order(2)
    void readTableTestNullSheetNameNull() {
        ReflectionTestUtils.setField(googleSheetsService, "spreadsheetId", "anyStringValue");
        ReflectionTestUtils.setField(googleSheetsService, "sheetName", null);

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> googleSheetsService.readTable()
        );

        assertEquals("Не верно указаны параметры Google Sheets(sheetName).", iae.getMessage());
    }

    @Nested
    class PreInstalled{
        @BeforeEach
        void setUp() throws IOException {
            ReflectionTestUtils.setField(googleSheetsService, "spreadsheetId", "anyStringValue");
            ReflectionTestUtils.setField(googleSheetsService, "sheetName", "anyStringValue");

            given(googleSheets.spreadsheets()).willReturn(spreadsheets);
            given(spreadsheets.values()).willReturn(values);
            given(values.get(anyString(), anyString())).willReturn(get);

            List<Object> row = List.of("1234", "UserName", "+79149879", "fgh@mail.kp");
            List<List<Object>> rows = List.of(row);
            testValueRange.setValues(rows);
        }

        @Test
        @DisplayName("3. Testing table reading. Ok.")
        @Order(3)
        void readTableTestOk() throws IOException {

            given(get.execute()).willReturn(testValueRange);

            List<List<String>> result = googleSheetsService.readTable();

            assertAll(
                    () -> assertNotNull(result),
                    () -> assertFalse(result.isEmpty()),
                    () -> assertEquals(testValueRange.getValues().size(), result.size())
            );
        }
    }
}
