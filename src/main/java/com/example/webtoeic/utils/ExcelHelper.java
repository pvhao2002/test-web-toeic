package com.example.webtoeic.utils;

import com.example.webtoeic.model.Question;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = {"Number", "Text", "Option1", "Option2", "Option3", "Option4", "Part", "Image"};
    static String SHEET = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Question> excelToQuestions(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Question> questionList = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Question question = new Question();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    var number = 0;
                    var text = "";

                    if (currentCell != null) {
                        if (currentCell.getCellType() == CellType.NUMERIC) {
                            number = (int) currentCell.getNumericCellValue();
                        } else if (currentCell.getCellType() == CellType.STRING) {
                            text = currentCell.getStringCellValue();
                        }
                    }
                    switch (Objects.requireNonNull(currentCell).getColumnIndex()) {
                        case 0:
                            question.setNumber(number);
                            break;

                        case 1:
                            question.setText(text);
                            break;
                        case 2:
                            question.setOption1(text);
                            break;

                        case 3:
                            question.setOption2(text);
                            break;
                        case 4:
                            question.setOption3(text);
                            break;
                        case 5:
                            question.setOption4(text);
                            break;
                        case 6:
                            question.setPart(number);
                            break;
                        case 7:
                            question.setImage(text);
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                questionList.add(question);
            }

            workbook.close();

            return questionList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
