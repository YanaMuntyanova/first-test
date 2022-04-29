package tests.Lesson8;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipParsTest {

ClassLoader cl = getClass().getClassLoader();
String nameXlsxName = "fileForTest.xlsx";
String nameCSVName = "csv.csv";
String namePdfName = "Tickets_451602.pdf";

    @Test
    void zipReadFileXlsxTest() throws IOException {

        ZipFile zf = new ZipFile(new File("src/test/resources/zip/fileForTest.zip"));
        ZipInputStream is = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("zip/fileForTest.zip")));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            if (entry.getName().equals(nameXlsxName)) {

                try (InputStream inputStream = zf.getInputStream(entry)) {
                    XLS xls = new XLS(inputStream);
                    String celValue = xls.excel.getSheetAt(0).getRow(2).getCell(0).getStringCellValue();
                    Assertions.assertEquals(celValue, "ЗВК.1");
                }
            }
        }
    }

    @Test
    void zipReadFileCSVTest() throws IOException, CsvException {

        ZipFile zf = new ZipFile(new File("src/test/resources/zip/csv.zip"));
        ZipInputStream is = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("zip/csv.zip")));
        ZipEntry entry;
        while ((entry = is.getNextEntry()) != null) {
            if (entry.getName().equals(nameCSVName)) {

                try (InputStream inputStream = zf.getInputStream(entry)) {
                    CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                    List<String[]> content = reader.readAll();
                    org.assertj.core.api.Assertions.assertThat(content).contains(
                            new String[]{"Небо", "Синее"},
                            new String[]{"Трава", "Зеленая"}
                    );
                }
            }
        }
    }

    @Test
    void zipReadFilePDFTest() throws IOException {

        ZipFile zf = new ZipFile(new File("src/test/resources/zip/Tickets_451602.zip"));
        ZipInputStream is = new ZipInputStream(Objects.requireNonNull(cl.getResourceAsStream("zip/Tickets_451602.zip")));
        ZipEntry entry;

        while ((entry = is.getNextEntry()) != null) {
            if (entry.getName().equals(namePdfName)) {
                try (InputStream inputStream = zf.getInputStream(entry)) {
                    PDF pdf = new PDF(inputStream);
                    Assertions.assertEquals(3, pdf.numberOfPages);
                }
            }

        }
    }

}
