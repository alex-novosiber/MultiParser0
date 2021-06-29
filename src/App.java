import JsoupStaff.JsoupMethods;
import confData.ConfigRW;
import csvToXlsx.CsvToXLSX;
import dateAndTime.DateAndTime;
import printReadme.PrintReadme;
import writeToFiles.WriteColNames;
import writeToFiles.WriteRows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class App {

    public static ArrayList<String> tableCol1 = new ArrayList<String>();
    public static ArrayList<String> tableCol2 = new ArrayList<String>();
    public static ArrayList<String> tableCol3 = new ArrayList<String>();
    public static ArrayList<String> tableCol4 = new ArrayList<String>();
    public static ArrayList<String> tableCol5 = new ArrayList<String>();
    public static ArrayList<String> tableCol6 = new ArrayList<String>();
    public static ArrayList<String> tableCol7 = new ArrayList<String>();
    public static ArrayList<String> tableCol8 = new ArrayList<String>();
    public static ArrayList<String> tableCol9 = new ArrayList<String>();
    public static ArrayList<String> tableCol10 = new ArrayList<String>();
    public static ArrayList<String> tableCol11 = new ArrayList<String>();
    public static ArrayList<String> tableCol12 = new ArrayList<String>();
    public static ArrayList<String> tableCol13 = new ArrayList<String>();
    public static ArrayList<String> tableCol14 = new ArrayList<String>();
    public static ArrayList<String> tableCol15 = new ArrayList<String>();
    public static ArrayList<String> tableCol16 = new ArrayList<String>();
    public static ArrayList<String> tableCol17 = new ArrayList<String>();
    public static ArrayList<String> tableCol18 = new ArrayList<String>();
    public static ArrayList<String> tableCol19 = new ArrayList<String>();
    public static ArrayList<String> tableCol20 = new ArrayList<String>();
    static Map<String, String> confMap;
    static String currentDateAndTime;

    public static void main(String[] args) throws IOException, InterruptedException {


        currentDateAndTime = DateAndTime.getDateTime();


        // Fill Map keys and values from config
        confMap = ConfigRW.addConfigToMap();

        // Create CSV file and WRITE first row with columnNames
        WriteColNames.write();

        // Getting sleeping time
        int threadSleep = Integer.parseInt(confMap.get("threadSleep"));

        // Getting first page for searching PagesCount
        JsoupMethods.getFirstPage();

        // Getting quantity of founded pages
        int pagesTotal = JsoupMethods.findPagesCount();


        //****************************   Перебираем страницы   ****************************


        for (int counterOfPages = 1; counterOfPages <= pagesTotal; ) {

            // getting page №[ counterOfPages ]
            JsoupMethods.getPage(counterOfPages);

            System.out.println("Обработка страницы № " + counterOfPages);


            // getting ALL(adsPerPageLimit) Ad blocks by css selector div._main (e.c. 25 Ad blocks)
            JsoupMethods.getPageBlocks();


//            System.out.println("------------------------------");

            // clear tableCols from previous loaded data
            ClearCols.clear();

            tableCol1.addAll(JsoupMethods.getColContent("col1"));
//            System.out.println(" col1 in App class = " + tableCol1.size() + " " + tableCol1);

            tableCol2.addAll(JsoupMethods.getColContent("col2"));
//            System.out.println(" col2 in App class = " + tableCol2.size() + " " + tableCol2);

            tableCol3.addAll(JsoupMethods.getColContent("col3"));
//            System.out.println(" col3 in App class = " + tableCol3.size() + " " + tableCol3);

            tableCol4.addAll(JsoupMethods.getColContent("col4"));
//            System.out.println(" col4 in App class = " + tableCol4.size() + " " + tableCol4);

            tableCol5.addAll(JsoupMethods.getColContent("col5"));
//            System.out.println(" col5 in App class = " + tableCol5.size() + " " + tableCol5);

            tableCol6.addAll(JsoupMethods.getColContent("col6"));
//            System.out.println(" col6 in App class = " + tableCol6.size() + " " + tableCol6);

            tableCol7.addAll(JsoupMethods.getColContent("col7"));
//            System.out.println(" col7 in App class = " + tableCol7.size() + " " + tableCol7);

            tableCol8.addAll(JsoupMethods.getColContent("col8"));
//            System.out.println(" col8 in App class = " + tableCol8.size() + " " + tableCol8);

            tableCol9.addAll(JsoupMethods.getColContent("col9"));
//            System.out.println(" col9 in App class = " + tableCol9.size() + " " + tableCol9);

            tableCol10.addAll(JsoupMethods.getColContent("col10"));
//            System.out.println(" col10 in App class = " + tableCol10.size() + " " + tableCol10);

            tableCol11.addAll(JsoupMethods.getColContent("col11"));
//            System.out.println(" col11 in App class = " + tableCol11.size() + " " + tableCol11);

            tableCol12.addAll(JsoupMethods.getColContent("col12"));
//            System.out.println(" col12 in App class = " + tableCol12.size() + " " + tableCol12);

            tableCol13.addAll(JsoupMethods.getColContent("col13"));
//            System.out.println(" col13 in App class = " + tableCol13.size() + " " + tableCol13);

            tableCol14.addAll(JsoupMethods.getColContent("col14"));
//            System.out.println(" col14 in App class = " + tableCol14.size() + " " + tableCol14);

            tableCol15.addAll(JsoupMethods.getColContent("col15"));
//            System.out.println(" col15 in App class = " + tableCol15.size() + " " + tableCol15);


            WriteRows.write(tableCol1, tableCol2, tableCol3, tableCol4, tableCol5,
                    tableCol6, tableCol7, tableCol8, tableCol9, tableCol10,
                    tableCol11, tableCol12, tableCol13, tableCol14, tableCol15,
                    tableCol16, tableCol17, tableCol18, tableCol19, tableCol20);
            counterOfPages++;
            Thread.sleep(threadSleep * 1000);
        }


        CsvToXLSX.csvToXLSX(currentDateAndTime);

        PrintReadme.print();


    }
}


class ClearCols {

    public static void clear() {
        App.tableCol1.clear();
        App.tableCol2.clear();
        App.tableCol3.clear();
        App.tableCol4.clear();
        App.tableCol5.clear();
        App.tableCol6.clear();
        App.tableCol7.clear();
        App.tableCol8.clear();
        App.tableCol9.clear();
        App.tableCol10.clear();
        App.tableCol11.clear();
        App.tableCol12.clear();
        App.tableCol13.clear();
        App.tableCol14.clear();
        App.tableCol15.clear();
        App.tableCol16.clear();
        App.tableCol17.clear();
        App.tableCol18.clear();
        App.tableCol19.clear();
        App.tableCol20.clear();

    }


}