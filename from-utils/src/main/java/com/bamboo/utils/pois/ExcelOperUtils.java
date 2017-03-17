package com.bamboo.utils.pois;

import com.bamboo.utils.basic.RandomUtils;
import com.bamboo.utils.io.FileOperUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple Excel Oper Utils
 * Created by admin on 2017/3/17.
 */
public class ExcelOperUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelOperUtils.class);

    /**
     * auto opernate the data in memary
     * @throws Exception
     */
    public static void autoFlushDisk() throws Exception {
        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        /**
         *  SXSSF flushes sheet data in temporary files (a temp file per sheet) and the size of these temporary files can grow to a very large value.
         *  For example, for a 20 MB csv data the size of the temp xml becomes more than a gigabyte. If the size of the temp files is an issue, you can tell SXSSF to use gzip compression:
         *  wb.setCompressTempFiles(true); // temp files will be gzipped
         */

        Sheet sh = wb.createSheet();
        for(int rownum = 0; rownum < 1000; rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum = 0; cellnum < 10; cellnum++){
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

        }

        // Rows with rownum < 900 are flushed and not accessible
        for(int rownum = 0; rownum < 900; rownum++){
            Assert.assertNull(sh.getRow(rownum));
        }

        // ther last 100 rows are still in memory
        for(int rownum = 900; rownum < 1000; rownum++){
            Assert.assertNotNull(sh.getRow(rownum));
        }

        File outFile = FileOperUtils.createFile("/temp/sxssf.xlsx");
        FileOutputStream out = new FileOutputStream(outFile);
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }

    /**
     * manual opernate the data in memary
     * @throws Exception
     */
    public static void manualFlushDisk() throws Exception {
        SXSSFWorkbook wb = new SXSSFWorkbook(-1); // turn off auto-flushing and accumulate all rows in memory
        /**
         *  SXSSF flushes sheet data in temporary files (a temp file per sheet) and the size of these temporary files can grow to a very large value.
         *  For example, for a 20 MB csv data the size of the temp xml becomes more than a gigabyte. If the size of the temp files is an issue, you can tell SXSSF to use gzip compression:
         *  wb.setCompressTempFiles(true); // temp files will be gzipped
         */

        Sheet sh = wb.createSheet();
        for(int rownum = 0; rownum < 1000; rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum = 0; cellnum < 10; cellnum++){
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

            // manually control how rows are flushed to disk
            if(rownum % 100 == 0) {
                ((SXSSFSheet)sh).flushRows(100); // retain 100 last rows and flush all others

                // ((SXSSFSheet)sh).flushRows() is a shortcut for ((SXSSFSheet)sh).flushRows(0),
                // this method flushes all rows
            }

        }

        FileOutputStream out = new FileOutputStream("/temp/sxssf.xlsx");
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }

    /**
     * create xlsx by list data
     * @param sheetName
     * @param headTitle
     * @param outPath
     * @param list
     * @throws Exception
     */
    public static void createExcel(String sheetName, String headTitle, String outPath, List<List<String>> list) throws Exception {
        logger.info("begin generate excel...");
        SXSSFWorkbook wb = new SXSSFWorkbook();
        /**
         *  SXSSF flushes sheet data in temporary files (a temp file per sheet) and the size of these temporary files can grow to a very large value.
         *  For example, for a 20 MB csv data the size of the temp xml becomes more than a gigabyte. If the size of the temp files is an issue, you can tell SXSSF to use gzip compression:
         *  wb.setCompressTempFiles(true); // temp files will be gzipped
         */

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        Sheet sh = wb.createSheet(sheetName);
        // set column width
        sh.setColumnWidth(2, 3000);
        sh.setColumnWidth(5, 8000);
        sh.setColumnWidth(9, 12000);


        Row rowBlank = sh.createRow(0);
        Row rowHead = sh.createRow(1);
        for(int cellnum = 1; cellnum <= list.get(0).size(); cellnum++){
            Cell lieRow = rowHead.createCell(cellnum);
            // set style , ses http://blog.csdn.net/xingfengxu/article/details/41758945
            CellStyle innerCellStyle = wb.createCellStyle();
            innerCellStyle.setBorderBottom(BorderStyle.THIN);
            innerCellStyle.setBorderLeft(BorderStyle.THIN);
            innerCellStyle.setBorderTop(BorderStyle.THIN);
            innerCellStyle.setBorderRight(BorderStyle.THIN);
            innerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            //set font
            Font font = wb.createFont();
            font.setFontName("Courier New");
            font.setFontHeightInPoints((short) 14);
            innerCellStyle.setFont(font);
            lieRow.setCellStyle(innerCellStyle);
            lieRow.setCellValue(headTitle);
        }

        //set merge cell
        CellRangeAddress cellMerge = new CellRangeAddress(1, 1, 1, list.get(0).size());
        sh.addMergedRegion(cellMerge);

       /* for(int rownum = 2; rownum <= 200; rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum = 1; cellnum <= 10; cellnum++){
                Cell cell = row.createCell(cellnum);
                cell.setCellStyle(cellStyle);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }
        }*/
        for (int i = 0; i < list.size(); i++) {
            Row row = sh.createRow(i + 2);

            List<String> localList = list.get(i);
            for (int j = 0; j < localList.size(); j++) {
                Cell cell = row.createCell(j + 1);
                cell.setCellStyle(cellStyle);
                String value = localList.get(j);
                cell.setCellValue(value);
            }
        }

        File outFile = FileOperUtils.createFile(outPath);
        FileOutputStream out = new FileOutputStream(outFile);
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
        logger.info("generate excel has done.");
    }

    /**
     * init test data
     * @return
     */
    private static List<List<String>> initTestData() {
        List<List<String>> result = new ArrayList<List<String>>();
        for (int j = 0; j < 20; j++) {
            List<String> local = new ArrayList<String>();
            for (int i=0 ; i < 15; i++) {
                local.add(RandomUtils.getRandomCharByLength(3) + i);
            }
            result.add(local);
        }
        return result;
    }

    public static void main(String[] args) throws Throwable {
//        autoFlushDisk();
//        manualFlushDisk();
        List<List<String>> a = initTestData();
        createExcel("aaa", "bbb", "E:\\dd\\aa\\cc.xlsx", a);
    }

}
