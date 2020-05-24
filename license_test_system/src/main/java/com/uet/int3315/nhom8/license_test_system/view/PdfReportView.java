package com.uet.int3315.nhom8.license_test_system.view;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.uet.int3315.nhom8.license_test_system.model.dtos.ExamResultFullDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PdfReportView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> map, com.lowagie.text.Document document, com.lowagie.text.pdf.PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        InputStream inp = new FileInputStream(new File("template.xlsx"));
        Workbook workbook = WorkbookFactory.create(inp);
        List<ExamResultFullDTO> examResultList = (List<ExamResultFullDTO>) map.get("examResults");
        Sheet sheet = workbook.getSheetAt(0);

        int rowNum = 15;
        int index = 1;
        //Tổng số
        Row totalRow = sheet.getRow(12);
        totalRow.createCell(2).setCellValue(examResultList.size());

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        for (ExamResultFullDTO result : examResultList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(index++);
            row.createCell(1).setCellValue(result.getFullName());
            row.createCell(2).setCellValue(result.getUserName());
            row.createCell(3).setCellValue(result.getCoursename());
            row.createCell(4).setCellValue(result.getScore());
            row.createCell(5).setCellValue(result.getStatus());
            for (int i = 0; i < 6; i++) {
                row.getCell(i).setCellStyle(cellStyle);
            }
        }

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        workbook.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
////////
        InputStream inputStream = new ByteArrayInputStream(outArray);
        XSSFWorkbook my_xlsx_workbook = new XSSFWorkbook(inputStream);
        XSSFSheet my_worksheet = my_xlsx_workbook.getSheetAt(0);
        Iterator<Row> rowIterator = my_worksheet.iterator();
//        //Tạo pdf document
        Document iText_xls_2_pdf = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(iText_xls_2_pdf, byteArrayOutputStream);
        iText_xls_2_pdf.open();
//        //we have two columns in the Excel sheet, so we create a PDF table with two columns
        PdfPTable my_table = new PdfPTable(6);
//        //cell object to capture data
        PdfPCell table_cell;
//        //Loop through rows.
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next(); //Fetch CELL
                switch (cell.getCellTypeEnum()) { //Identify CELL type

                    case STRING:
                        table_cell = new PdfPCell(new Phrase(cell.getStringCellValue()));
                        my_table.addCell(table_cell);
                        break;
                }
                //next line
            }
        }
        iText_xls_2_pdf.add(my_table);
        iText_xls_2_pdf.close();

        httpServletResponse.setContentType("application/x-pdf;charset=UTF-8");
        httpServletResponse.setHeader("Content-disposition", "inline; filename=pdfReport.pdf");
        OutputStream out = httpServletResponse.getOutputStream();
        out.write(byteArrayOutputStream.toByteArray());
        workbook.close();
    }
}
