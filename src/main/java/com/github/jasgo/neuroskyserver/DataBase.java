package com.github.jasgo.neuroskyserver;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;

public class DataBase {

    private String path;

    public DataBase(String path) {
        this.path = path;
    }
    public void saveData(JSONArray array) {
        long lowAlpha = 0;
        long highAlpha = 0;
        long lowBeta = 0;
        long highBeta = 0;
        long lowGamma = 0;
        long highGamma = 0;
        long delta = 0;
        long theta = 0;
        try {
            FileOutputStream fileout = new FileOutputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFRow row;
            XSSFCell cell;

            int rowIndex = 0;

            XSSFSheet sheet = workbook.createSheet();
            XSSFFont font = workbook.createFont();
            font.setBold(true);

            CellStyle style = workbook.createCellStyle();
            style.setFont(font);
            style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
            style.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.index);

            row = sheet.createRow(rowIndex++); //행 객체 추가
            cell = row.createCell((short) 0); // 추가한 행에 셀 객체 추가
            cell.setCellStyle(style);
            cell.setCellValue("LowAlpha");
            cell = row.createCell((short) 1);
            cell.setCellStyle(style);
            cell.setCellValue("HighAlpha");
            cell = row.createCell((short) 2);
            cell.setCellStyle(style);
            cell.setCellValue("LowBeta");
            cell = row.createCell((short) 3);
            cell.setCellStyle(style);
            cell.setCellValue("HighBeta");
            cell = row.createCell((short) 4);
            cell.setCellStyle(style);
            cell.setCellValue("LowGamma");
            cell = row.createCell((short) 5);
            cell.setCellStyle(style);
            cell.setCellValue("HighGamma");
            cell = row.createCell((short) 6);
            cell.setCellStyle(style);
            cell.setCellValue("Delta");
            cell = row.createCell((short) 7);
            cell.setCellStyle(style);
            cell.setCellValue("Theta");

            for (Object o : array) {
                JSONObject json = (JSONObject) ((JSONObject) o).get("eegPower");
                lowAlpha = (long) json.get("lowAlpha");
                highAlpha = (long) json.get("highAlpha");
                lowBeta = (long) json.get("lowBeta");
                highBeta = (long) json.get("highBeta");
                lowGamma = (long) json.get("lowGamma");
                highGamma = (long) json.get("highGamma");
                delta = (long) json.get("delta");
                theta = (long) json.get("theta");
                row = sheet.createRow(rowIndex++);
                cell = row.createCell((short) 0);
                cell.setCellValue(lowAlpha);
                cell = row.createCell((short) 1);
                cell.setCellValue(highAlpha);
                cell = row.createCell((short) 2);
                cell.setCellValue(lowBeta);
                cell = row.createCell((short) 3);
                cell.setCellValue(highBeta);
                cell = row.createCell((short) 4);
                cell.setCellValue(lowGamma);
                cell = row.createCell((short) 5);
                cell.setCellValue(highGamma);
                cell = row.createCell((short) 6);
                cell.setCellValue(delta);
                cell = row.createCell((short) 7);
                cell.setCellValue(theta);
            }

            workbook.write(fileout);
            fileout.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
