package com.huifa.paper.parent;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kchen on 2016-11-04.
 */
public class ReadExcel {
    /**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<Shop> readExcel(String path) throws IOException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Common.EMPTY.equals(postfix)) {
                if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    return readXls(path);
                } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                System.out.println(path + Common.NOT_EXCEL_FILE);
            }
        }
        return null;
    }

    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public List<Shop> readXlsx(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Shop shop = null;
        List<Shop> list = new LinkedList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    shop = new Shop();
                    XSSFCell provinceName = xssfRow.getCell(0);
                    XSSFCell provinceId = xssfRow.getCell(1);
                    XSSFCell cityName = xssfRow.getCell(2);
                    XSSFCell cityId = xssfRow.getCell(3);
                    XSSFCell zoneName = xssfRow.getCell(4);
                    XSSFCell zoneId = xssfRow.getCell(5);
                    XSSFCell shopName = xssfRow.getCell(6);
                    XSSFCell shopNum = xssfRow.getCell(7);
                    shop.setProvinceName(getValue(provinceName));
                    shop.setProvinceId(getValue(provinceId));
                    shop.setCityName(getValue(cityName));
                    shop.setCityId(getValue(cityId));
                    shop.setZoneName(getValue(zoneName));
                    shop.setZoneId(getValue(zoneId));
                    shop.setShopName(getValue(shopName));
                    shop.setShopNum(getValue(shopNum));
                    list.add(shop);
                }
            }
        }
        return list;
    }

    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<Shop> readXls(String path) throws IOException {
        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        Shop shop = null;
        List<Shop> list = new LinkedList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    shop = new Shop();
                    HSSFCell provinceName = hssfRow.getCell(0);
                    HSSFCell provinceId = hssfRow.getCell(1);
                    HSSFCell cityName = hssfRow.getCell(2);
                    HSSFCell cityId = hssfRow.getCell(3);
                    HSSFCell zoneName = hssfRow.getCell(4);
                    HSSFCell zoneId = hssfRow.getCell(5);
                    HSSFCell shopName = hssfRow.getCell(6);
                    HSSFCell shopNum = hssfRow.getCell(7);
                    shop.setProvinceName(getValue(provinceName));
                    shop.setProvinceId(getValue(provinceId));
                    shop.setCityName(getValue(cityName));
                    shop.setCityId(getValue(cityId));
                    shop.setZoneName(getValue(zoneName));
                    shop.setZoneId(getValue(zoneId));
                    shop.setShopName(getValue(shopName));
                    shop.setShopNum(getValue(shopNum));
                    list.add(shop);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}