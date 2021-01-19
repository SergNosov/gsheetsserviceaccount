package com.svn.gsheetsserviceaccount.service.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.FastByteArrayOutputStream;

import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public OutputStream generatePdf(final Contact contact) {
        Assert.notNull(contact, "--- Сведения о контакте не должны быть null.");

        try {
            final FastByteArrayOutputStream byteOutputStream = new FastByteArrayOutputStream();
            final Document document = new Document();

            PdfWriter.getInstance(document, byteOutputStream);
            final BaseFont baseFont = BaseFont.createFont("Fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 16, Font.NORMAL, BaseColor.BLACK);

            document.open();
            document.add(new Paragraph("_id: " + contact.getId().toString(), font));
            document.add(new Paragraph("userId: " + contact.getCode(), font));
            document.add(new Paragraph("name: " + contact.getName(), font));
            document.add(new Paragraph("phone: " + contact.getPhone(), font));
            document.add(new Paragraph("e-mail: " + contact.getEmail(), font));
            document.close();

            log.info("--- contact:" + contact + "; byteArray.size():" + byteOutputStream.size());

            return byteOutputStream;
        } catch (IOException | DocumentException ex) {
            throw new RuntimeException("--- Не удалось создать pdf документ для: "+contact,ex);
        }
    }
}