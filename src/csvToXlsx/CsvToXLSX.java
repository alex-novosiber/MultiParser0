package csvToXlsx;

import confData.ConfigRW;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

public class CsvToXLSX {

    public static void csvToXLSX(String curDatTime) {


//        Properties config = new Properties();
//        InputStream iStream = null;
//
//        // Loading properties file from the path (relative path given here)
//        try {
//            iStream = new FileInputStream("Config.properties");
//            config.load(iStream);
////            System.out.println(config);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String fileName = ConfigRW.propsMap.get("outputFileName").toString();
//        FileWriter writer0 = new FileWriter(fileName + currentDateAndTime  + ".xlsx", true);
        try {
            String csvFileAddress = fileName + curDatTime + ".csv"; //csv file address
            String xlsxFileAddress = fileName + curDatTime + ".xlsx"; //xlsx file address
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("sheet1");
            String currentLine = null;
            int RowNum = 0;
            BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));

            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split("\\t");

                XSSFRow currentRow = sheet.createRow(RowNum);
                for (int i = 0; i < str.length; i++) {
                    currentRow.createCell(i).setCellValue(str[i]);
                }
                RowNum++;
            }

            FileOutputStream fileOutputStream = new FileOutputStream(xlsxFileAddress);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Done");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Exception in try");
        }
    }

}
