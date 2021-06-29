package JsoupStaff;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Methods {

    static ArrayList<String> tableCol = new ArrayList<String>();
    static ArrayList<String> tableColTemp = new ArrayList<String>();
    private static String tempString;


    public static ArrayList<String> parseRow(ArrayList<String> tableColName, Elements rows, String classToFind, String replaceFrom, String replaceTo) {
        tableCol.clear();
//        System.out.println(" Methods parseRow tableCol size BEFORE = " + tableCol.size());
        for (Element row : rows) {
            Elements td = row.getElementsByClass(classToFind);
            if (td.hasText()) {
                if ((td.text() != null) && (!td.text().isEmpty()) && (td.text() != "")) {
                    tempString = td.text().replaceAll(replaceFrom, replaceTo);    //    ","
                }
                tableCol.add(tempString);
            } else {
                tableCol.add(" - ");
            }
        }
        tableColName.addAll(tableCol);
//        System.out.println(" Methods parseRow tableCol size AFTER = " + tableCol.size());
        return tableColName;

    }


    public static ArrayList<String> patternParseTwoPatterns(String pattern1FromConfig, String pattern2FromConfig, String matchFrom, ArrayList<String> tableColName) {
        tableCol.clear();
        Pattern pattern1 = Pattern.compile(pattern1FromConfig);
        Pattern pattern2 = Pattern.compile(pattern2FromConfig);
        Matcher matcher1 = pattern1.matcher(matchFrom);
        while (matcher1.find()) {
            String tempMatcherString1 = matcher1.group();
            Matcher matcher2 = pattern2.matcher(tempMatcherString1);
            while (matcher2.find()) {
                String tempMatcherString2 = matcher2.group();
                tableColName.add(tempMatcherString2);
            }
        }
        return tableColName;
    }


//    public static void tableColClear(){
//        tableColTemp.clear();
//    }
}
