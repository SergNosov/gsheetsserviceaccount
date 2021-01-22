package com.svn.gsheetsserviceaccount.service.impl;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.svn.gsheetsserviceaccount.model.Contact;
import com.svn.gsheetsserviceaccount.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.FastByteArrayOutputStream;

import java.io.IOException;

@Slf4j
@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public byte[] generatePdf(final Contact contact) {
        Assert.notNull(contact, "--- Сведения о контакте не должны быть null.");

        try {
            final FastByteArrayOutputStream byteOutputStream = new FastByteArrayOutputStream();
            final Document document = new Document();

            PdfWriter.getInstance(document, byteOutputStream);
            //PdfWriter.getInstance(document, new FileOutputStream("d:/temp/contact.pdf"));

            final BaseFont baseFont = BaseFont.createFont("Fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 16, Font.NORMAL, BaseColor.BLACK);

            document.open();
            document.add(new Paragraph("_id: " + contact.getId().toString(), font));
            document.add(new Paragraph("userId: " + contact.getCode(), font));
            document.add(new Paragraph("name: " + contact.getName(), font));
            document.add(new Paragraph("phone: " + contact.getPhone(), font));
            document.add(new Paragraph("e-mail: " + contact.getEmail(), font));
            document.add(generateQRCode(contact));
            document.close();

            log.info("--- contact:" + contact + "; byteArray.size():" + byteOutputStream.size());

            return byteOutputStream.toByteArray();
        } catch (IOException | DocumentException ex) {
            log.error("--- Не удалось создать pdf документ для: "+contact+"; exception: "+ ex.getMessage());

            throw new RuntimeException("--- Не удалось создать pdf документ для: "+ contact +
                    "; " + ex.getMessage(), ex);
        }
    }

    private Image generateQRCode(Contact contact) throws BadElementException {
        BarcodeQRCode barcodeQRCode = new BarcodeQRCode(contact.getCode(), 250, 250, null);
        return barcodeQRCode.getImage();
    }
}
