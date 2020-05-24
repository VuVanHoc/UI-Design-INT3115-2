package com.uet.int3315.nhom8.license_test_system.view;

import com.uet.int3315.nhom8.license_test_system.model.dtos.ExamResultFullDTO;
import com.uet.int3315.nhom8.license_test_system.model.entity.ExamResult;

import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

public class ExcelReportView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        InputStream inp = new FileInputStream(new File("template.xlsx"));
        workbook = WorkbookFactory.create(inp);
        List<ExamResultFullDTO> examResultList = (List<ExamResultFullDTO>) model.get("examResults");
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
            for(int i = 0; i < 6; i++){
                row.getCell(i).setCellStyle(cellStyle);
            }
        }

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        workbook.write(outByteStream);
        byte [] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=\"ReportExam.xlsx\"");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();

        workbook.close();
    }
}
