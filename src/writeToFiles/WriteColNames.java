package writeToFiles;

import confData.ConfigRW;
import dateAndTime.DateAndTime;

import java.io.FileWriter;
import java.io.IOException;

public class WriteColNames {

    static String currentDateAndTime = DateAndTime.currentDateAndTime;
    static String outputFileName = ConfigRW.propsMap.get("outputFileName").toString();


    static String col1Name = ConfigRW.propsMap.get("col1Name") + "\t";
    static String col2Name = ConfigRW.propsMap.get("col2Name") + "\t";
    static String col3Name = ConfigRW.propsMap.get("col3Name") + "\t";
    static String col4Name = ConfigRW.propsMap.get("col4Name") + "\t";
    static String col5Name = ConfigRW.propsMap.get("col5Name") + "\t";
    static String col6Name = ConfigRW.propsMap.get("col6Name") + "\t";
    static String col7Name = ConfigRW.propsMap.get("col7Name") + "\t";
    static String col8Name = ConfigRW.propsMap.get("col8Name") + "\t";
    static String col9Name = ConfigRW.propsMap.get("col9Name") + "\t";
    static String col10Name = ConfigRW.propsMap.get("col10Name") + "\t";
    static String col11Name = ConfigRW.propsMap.get("col11Name") + "\t";
    static String col12Name = ConfigRW.propsMap.get("col12Name") + "\t";
    static String col13Name = ConfigRW.propsMap.get("col13Name") + "\t";
    static String col14Name = ConfigRW.propsMap.get("col14Name") + "\t";
    static String col15Name = ConfigRW.propsMap.get("col15Name") + "\t";
    static String col16Name = ConfigRW.propsMap.get("col16Name") + "\t";
    static String col17Name = ConfigRW.propsMap.get("col17Name") + "\t";
    static String col18Name = ConfigRW.propsMap.get("col18Name") + "\t";
    static String col19Name = ConfigRW.propsMap.get("col19Name") + "\t";
    static String col20Name = ConfigRW.propsMap.get("col20Name") + "\n";


    public static void write() {
        try {
            FileWriter writer0 = new FileWriter(outputFileName + currentDateAndTime + ".csv", true);
            writeColHeaders(col1Name, col2Name, col3Name, col4Name, col5Name, col6Name, col7Name, col8Name, col9Name, col10Name, writer0);
            writeColHeaders(col11Name, col12Name, col13Name, col14Name, col15Name, col16Name, col17Name, col18Name, col19Name, col20Name, writer0);
            writer0.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeColHeaders(
            String col1Name, String col2Name, String col3Name, String col4Name,
            String col5Name, String col6Name, String col7Name, String col8Name,
            String col9Name, String col10Name,
            FileWriter writer0)
            throws IOException {
        writer0.write(col1Name);
        writer0.write(col2Name);
        writer0.write(col3Name);
        writer0.write(col4Name);
        writer0.write(col5Name);
        writer0.write(col6Name);
        writer0.write(col7Name);
        writer0.write(col8Name);
        writer0.write(col9Name);
        writer0.write(col10Name);
    }


}
