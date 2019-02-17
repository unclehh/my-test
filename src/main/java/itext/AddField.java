package itext;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PushbuttonField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddField {

    public static final String SRC = "D:\\projects\\my\\my-test\\src\\main\\resource\\pdfs\\form.pdf";
    public static final String DEST = "D:\\projects\\my\\my-test\\src\\main\\resource\\pdfs\\form_copy.pdf";


    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddField().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PushbuttonField button = new PushbuttonField(
                stamper.getWriter(), new Rectangle(36, 700, 72, 730), "post");
        button.setText("POST");
        button.setBackgroundColor(new GrayColor(0.7f));
        button.setVisibility(PushbuttonField.VISIBLE_BUT_DOES_NOT_PRINT);
        PdfFormField submit = button.getField();
        submit.setAction(PdfAction.createSubmitForm(
                "http://itextpdf.com:8180/book/request", null,
                PdfAction.SUBMIT_HTML_FORMAT | PdfAction.SUBMIT_COORDINATES));
        stamper.addAnnotation(submit, 1);
        stamper.close();
    }
}