package com.github.jasgo.neuroskyserver;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.json.simple.JSONObject;

import java.io.*;

public class DataBase {

    public static void main(String[] args) {

        try {
            FileInputStream file = new FileInputStream("test.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFRow row = null;
            XSSFCell cell = null;

            int rowIndex = 0;
            int columnIndex = 0;

            XSSFSheet sheet = workbook.getSheetAt(0);

            row = sheet.createRow(rowIndex++); //행 객체 추가
            cell = row.createCell((short) 0); // 추가한 행에 셀 객체 추가
            cell.setCellValue("타이틀 입니다.");

            row = sheet.createRow(rowIndex++); //헤더 01
            cell = row.createCell((short) 0);
            cell.setCellValue("헤더01 셀01");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveData(JSONObject json) {
        
    }
}
