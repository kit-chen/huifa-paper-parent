package com.huifa.paper.parent;

import java.io.IOException;
import java.util.List;

/**
 * Created by kchen on 2016-11-04.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String excel2003_2007 = "F:/shop.xlsx";//Common.STUDENT_INFO_XLS_PATH;
        String excel2010 = "F:/shop.xlsx";//Common.STUDENT_INFO_XLSX_PATH;
        // read the 2003-2007 excel
//        List<Shop> list = new ReadExcel().readExcel(excel2003_2007);
//        if (list != null) {
//            for (Shop shop : list) {
//                System.out.println(shop.toString());
//            }
//        }
//        System.out.println("======================================");
        // read the 2010 excel
        List<Shop> list1 = new ReadExcel().readExcel(excel2010);
        if (list1 != null) {
            for (Shop shop : list1) {
                String sql = "INSERT INTO `shop`(`provinceId`,`cityId`,`zoneId`,`shopName`,`notes`,`deleteStatus`) VALUES (%s,%s,%s,'%s','',%s);";
                System.out.println(String.format(sql,shop.getProvinceId(),shop.getCityId(),shop.getZoneId(),shop.getShopName(),1));
            }
        }
    }
}
