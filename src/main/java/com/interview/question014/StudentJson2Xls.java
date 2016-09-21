package com.interview.question014;

import com.interview.question004.Words;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;


/**
 * 第 0014 题： 纯文本文件 student.txt为学生信息, 里面的内容（包括花括号）如下所示：
 */
public class StudentJson2Xls {

    public static final String STUDENT_TXT = "student.txt";
    public static final String STUDENT_XLS = "student.xls";
    public static final String JSON_DIR = "json";

    public static void convertJson2Xls() throws IOException {
        File dir = new File(".");

        String txtFilePath = dir.getCanonicalPath() + File.separator + JSON_DIR + File.separator + STUDENT_TXT;
        String json = Words.readFile(new File(txtFilePath));
        JSONObject jsonObject = new JSONObject(json);

        String xlsFilePath = dir.getCanonicalPath() + File.separator + JSON_DIR + File.separator + STUDENT_XLS;
        write2Excel(jsonObject.toMap(), new FileOutputStream(xlsFilePath));
    }

    private static void write2Excel(Map<String, Object> students, OutputStream os) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("student");
        int i = students.size();
        for (Map.Entry<String, Object> studentEntry : students.entrySet()) {
            Row row = sheet.createRow(--i);
            row.createCell(0).setCellValue(studentEntry.getKey());
            List<String> list = (List<String>) studentEntry.getValue();
            for (int j = 0; j < list.size(); j++) {
                Cell cell = row.createCell(j+1);
                Object obj = list.get(j);
                setCellValue(cell, obj);
            }
        }
        workbook.write(os);
        os.close();
    }

    private static void setCellValue(Cell cell, Object obj) {
        if (obj == null){
            cell.setCellValue("");
        } else if (obj instanceof String) {
            cell.setCellValue((String) obj);
        } else if (obj instanceof Integer) {
            cell.setCellValue((Integer) obj);
        } else if (obj instanceof Long) {
            cell.setCellValue((Long) obj);
        } else if (obj instanceof Double) {
            cell.setCellValue((Double) obj);
        } else if (obj instanceof Float) {
            cell.setCellValue((Float) obj);
        }
    }

}
