package com.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    @Retention(RetentionPolicy.RUNTIME)
    public @interface ColumnName {

        String value() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface ColumnType {
        enum ExcelColumnType {
            STRING,
            INTEGER,
            DOUBLE
        }
        ExcelColumnType value() default ExcelColumnType.STRING;
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface FileName {
        String value() default "report.xls";
    }

    static List<String> parseHeader(Object data) {
        List<String> l = new ArrayList<>();
        for (Field field : data.getClass().getDeclaredFields()) {
            ColumnName fieldAnnotation = field.getAnnotation(ColumnName.class);
            if (fieldAnnotation != null) {
                l.add(fieldAnnotation.value());
            }
        }
        return l;
    }

    static List<Pair<String,ColumnType.ExcelColumnType>> parseContent(Object data) throws IllegalAccessException {
        List<Pair<String,ColumnType.ExcelColumnType>> list = new ArrayList<>();
        for (Field field : data.getClass().getDeclaredFields()) {
            ColumnName columnNameAnnotation = field.getAnnotation(ColumnName.class);
            ColumnType columnTypeAnnotation = field.getAnnotation(ColumnType.class);
            if (columnNameAnnotation != null) {
                field.setAccessible(true);
                if(null!=field.get(data)&&""!=field.get(data)){
                    list.add(Pair.of(field.get(data).toString(), null == columnTypeAnnotation ? ColumnType.ExcelColumnType.STRING : columnTypeAnnotation.value()));
                }else{
                    list.add(Pair.of("", null == columnTypeAnnotation ? ColumnType.ExcelColumnType.STRING : columnTypeAnnotation.value()));
                }
            }
        }
        return list;
    }


    static String parseFileName(Object data) {

        FileName fileNameAnnotation = data.getClass().getAnnotation(FileName.class);
        assert fileNameAnnotation != null;
        return fileNameAnnotation.value();

    }

    static void addTitle(Sheet sheet, List<String> rowData) {
        int rowNum = 0;
        if (sheet.getPhysicalNumberOfRows() != 0) {
            rowNum = sheet.getLastRowNum() + 1;
        }
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < rowData.size(); i++) {
            row.createCell(i).setCellValue(rowData.get(i));
        }
    }

    static void addRow(HSSFSheet sheet, List<Pair<String,ColumnType.ExcelColumnType>> rowData, HSSFWorkbook wb, HSSFCellStyle contextStyle) {
        int rowNum = 0;
        if (sheet.getPhysicalNumberOfRows() != 0) {
            rowNum = sheet.getLastRowNum() + 1;
        }
        HSSFRow row = sheet.createRow(rowNum);
        HSSFDataFormat df = wb.createDataFormat();
        for (int i = 0; i < rowData.size(); i++) {
            String data = rowData.get(i).getLeft();
            HSSFCell contentCell = row.createCell(i);
            if (StringUtils.isEmpty(data)) {
                contentCell.setCellStyle(contextStyle);
                contentCell.setCellValue(data);
                continue;
            }
            switch (rowData.get(i).getRight()) {
                case DOUBLE:
                    contextStyle.setDataFormat(df.getBuiltinFormat("#,##0.00"));
                    contentCell.setCellStyle(contextStyle);
                    contentCell.setCellValue(Double.parseDouble(data));
                    break;
                case INTEGER:
                    contextStyle.setDataFormat(df.getBuiltinFormat("#,#0"));
                    contentCell.setCellStyle(contextStyle);
                    contentCell.setCellValue(Integer.parseInt(data));
                    break;
                default:
                    contentCell.setCellStyle(contextStyle);
                    contentCell.setCellValue(data);
                    break;
            }
            contentCell = null;
        }
    }

    public static ByteArrayInputStream createExcel(List<?> dataList) throws IllegalAccessException, IOException {
        assert dataList != null;
        assert !dataList.isEmpty();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet0");
        if (null != dataList && dataList.size() != 0) {
            Object firstData = dataList.get(0);
            List<String> headers = parseHeader(firstData);
            addTitle(sheet, headers);
            HSSFCellStyle contextStyle =wb.createCellStyle();
            for (Object data : dataList) {
                List<Pair<String,ColumnType.ExcelColumnType>> contents = parseContent(data);
                addRow(sheet, contents, wb, contextStyle);
            }
        }
        try {
            wb.write(bos);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        return bis;
    }

}
