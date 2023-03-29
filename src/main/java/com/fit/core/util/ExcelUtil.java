package com.fit.core.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Title: ExcelUtil
 * @Package: com.fit.core.util
 * @Description:
 * @Author: Aim
 * @date: 4/29/18
 * @Version: V1.0
 */
public class ExcelUtil {

    public static void main(String[] args) throws IOException {
        String[] fields = {"订单号", "付款时间"};
        List<Map<String, Object>> list = ExcelUtil.readExcel("/Users/jorton/Documents/project/queenmax/尚恩小倩_04260555524026n.xlsx", fields);

        for (Map map : list) {
            for (Object o : map.values()) {
                System.out.println(o);
            }
            System.out.println();
        }
    }

//    public static List<Map> readExcel(File file, String[] fields) throws IOException {
//        return readExcel(new FileInputStream(file), fields);
//    }

    public static List<Map<String, Object>> readExcel(String fPath, String[] fields) throws IOException {
        List<Map<String, Object>> list = null;

        try {
            list = readExcel(new FileInputStream(fPath), fields);//2007 -
        } catch (OfficeXmlFileException e) {
            list = readExcelPlus(new FileInputStream(fPath), fields);//2007 +
        }
        return list;
    }

    /**
     * excel读取2007-
     *
     * @author Aim
     */
    private static List<Map<String, Object>> readExcel(InputStream is, String[] fields) throws IOException, OfficeXmlFileException {

        List<Map<String, Object>> list = new ArrayList<>();

        HSSFWorkbook wk = new HSSFWorkbook(is);
        HSSFSheet sheet = wk.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            Map<String, Object> map = new HashMap<>();
            short cellNum = row.getLastCellNum();

            for (int j = 0; j < cellNum && j < fields.length; j++) {
                HSSFCell cell = row.getCell(j);

                int cellType = cell.getCellType();
                if (cellType == 0) {
                    map.put(fields[j], (long) cell.getNumericCellValue() + "");
                } else {
                    map.put(fields[j], cell.getStringCellValue());
                }
            }
            list.add(map);
        }
        return list;
    }

    /**
     * excel读取 2007+
     *
     * @author Lxyer 2016/8/1 10:32.
     */
    private static List<Map<String, Object>> readExcelPlus(InputStream is, String[] fields) {
        List<Map<String, Object>> list = new ArrayList<>();

        XSSFWorkbook wk = null;
        try {
            wk = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = wk.getSheetAt(0);

        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            Map<String, Object> map = new HashMap<>();
            short cellNum = row.getLastCellNum();

            for (int j = 0; j < cellNum && j < fields.length; j++) {
                XSSFCell cell = row.getCell(j);

                if (!Objects.isNull(cell)) {
                    int cellType = cell.getCellType();
                    if (cellType == 0) {
                        map.put(fields[j], (long) cell.getNumericCellValue() + "");
                    } else {
                        map.put(fields[j], cell.getStringCellValue());
                    }
                } else {
                    map.put(fields[j], "");
                }

            }
            list.add(map);
        }

        return list;
    }

}
