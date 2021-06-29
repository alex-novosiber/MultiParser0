package JsoupStaff;

import confData.ConfigRW;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsoupMethods {

    // dzzhetj
    public static Map propsMap = ConfigRW.propsMap;
    public static Document document;
    public static Document page;
    public static Elements pageBlocks;
    static ArrayList<String> tableCol = new ArrayList<String>();
    static ArrayList<String> tableColTemp = new ArrayList<String>();
    static String URLfromConfig = propsMap.get("url").toString();
    static int maxbodySyzeFromConfig = Integer.parseInt(propsMap.get("maxbodySyze").toString());
    static int adsPerPageLimit = Integer.parseInt(propsMap.get("adsPerPageLimit").toString());
    static String totalAdsClassFromConfig = propsMap.get("adsFounded").toString();
    static int jsoupConnectTimeOut = Integer.parseInt(propsMap.get("jsoupConnectTimeOut").toString());
    static String divMain = propsMap.get("divMain").toString();
    private static String tempString;

    /**
     * <p> The returned page (as Document)
     * ,  have 1 parameter :
     *
     * @param counterOfPages - get quantity of pages,
     *                       founded on a first page  with method {@link JsoupMethods#findPagesCount()}
     *
     *                       <p>Jsoup.connect (URLfromConfig + adsPerPageLimit + "&page=" + counterOfPages)
     *                       .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
     *                       .referrer("http://www.google.com")
     *                       .timeout(1000 * jsoupConnectTimeOut) //it's in milliseconds, so this means 5 seconds.
     *                       .maxBodySize(maxbodySyzeFromConfig)
     *                       .get();
     * @return {@link Document}  page;
     */
    public static Document getPage(int counterOfPages) throws IOException {
        page = Jsoup.connect(URLfromConfig + adsPerPageLimit + "&page=" + counterOfPages)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .timeout(1000 * jsoupConnectTimeOut) //it's in milliseconds, so this means 5 seconds.
                .maxBodySize(maxbodySyzeFromConfig)
                .get();
//        System.out.println(" JsoupMethods.getPage " + URLfromConfig + adsPerPageLimit + "&page=" + counterOfPages);
//        TempFileWriter.write(" JsoupMethods.getPage = page  " + page);
        return page;
    }

    public static Document getFirstPage() throws IOException {


        document = Jsoup.connect(URLfromConfig + adsPerPageLimit)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                .referrer("http://www.google.com")
                .maxBodySize(maxbodySyzeFromConfig)
                .timeout(1000 * jsoupConnectTimeOut) //it's in milliseconds, so this means 5 seconds.
                .get();
        return document;
    }


    public static int findPagesCount() {

        String adsFounded = document.select(totalAdsClassFromConfig).text();
        String tempStringFounded = adsFounded.replaceAll("\\D*", "");    //  " Квартиры 996 объявлений "

        int digit = Integer.parseInt(tempStringFounded);
        System.out.println(" Найдено " + digit + " объявлений.");
        digit = digit / adsPerPageLimit;
        int pagesTotal = digit + 1;
        System.out.println(" Найдено " + pagesTotal + " страниц.");

        return pagesTotal;
    }

    public static Elements getPageBlocks() {
        pageBlocks = page.select(divMain);
        return pageBlocks;
    }


    public static ArrayList<String> getColContent(String colName) {
        boolean colUse = ConfigRW.getBool(colName + "Use");
        String colMethodFromConfig = propsMap.get(colName + "Method").toString();


        if (colUse) {
            tableCol.clear();
            if (colMethodFromConfig.equals("parseRow")) {
                String colClassToFindFromConfig = propsMap.get(colName + "Class").toString();
                String colReplaceFromFromConfig = propsMap.get(colName + "ReplaceFrom").toString();
                String colReplaceToFromConfig = propsMap.get(colName + "ReplaceTo").toString();
                tableCol = parseRow(pageBlocks, colClassToFindFromConfig, colReplaceFromFromConfig, colReplaceToFromConfig);
            }
            if (colMethodFromConfig.equals("parseRowDoubleFor")) {
                String colClassToFindFromConfig = propsMap.get(colName + "Class").toString();
                String colClass2ToFindFromConfig = propsMap.get(colName + "Class2").toString();
                String colClass3ToFindFromConfig = propsMap.get(colName + "Class3").toString();

                tableCol = parseRowDoubleFor(pageBlocks, colClassToFindFromConfig, colClass2ToFindFromConfig, colClass3ToFindFromConfig);
            }
            if (colMethodFromConfig.equals("getElementsContainingOwnText")) {
                String colClassToFindFromConfig = propsMap.get(colName + "Class").toString();
                String getElementsContainingOwnTextFromConfig = propsMap.get(colName + "getElementsContainingOwnText").toString();
                String colReplaceFromFromConfig = propsMap.get(colName + "ReplaceFrom").toString();
                String colReplaceToFromConfig = propsMap.get(colName + "ReplaceTo").toString();
                tableCol = getElementsContainingOwnText(pageBlocks, colClassToFindFromConfig, getElementsContainingOwnTextFromConfig, colReplaceFromFromConfig, colReplaceToFromConfig);
            }
            if (colMethodFromConfig.equals("getElementsMatchingOwnText")) {
                String colClassToFindFromConfig = propsMap.get(colName + "Class").toString();
                String getElementsMatchingOwnTextFromConfig = propsMap.get(colName + "getElementsMatchingOwnText").toString();
                String colReplaceFromFromConfig = propsMap.get(colName + "ReplaceFrom").toString();
                String colReplaceToFromConfig = propsMap.get(colName + "ReplaceTo").toString();
                tableCol = getElementsMatchingOwnText(pageBlocks, colClassToFindFromConfig, getElementsMatchingOwnTextFromConfig, colReplaceFromFromConfig, colReplaceToFromConfig);
            }
            if (colMethodFromConfig.equals("patternParseTwoPatterns")) {
                String selectScriptFromConfig = propsMap.get(colName + "SelectScript").toString();
                String pattern1FromConfig = propsMap.get(colName + "Pattern1").toString();
                String pattern2FromConfig = propsMap.get(colName + "Pattern2").toString();
                tableCol = patternParseTwoPatterns(selectScriptFromConfig, pattern1FromConfig, pattern2FromConfig);
            }
            if (colMethodFromConfig.equals("patternParseOnce")) {
                String selectScriptFromConfig = propsMap.get(colName + "SelectScript").toString();
                String pattern1FromConfig = propsMap.get(colName + "Pattern1").toString();
                String pattern2FromConfig = propsMap.get(colName + "Pattern2").toString();
                String colReplaceFrom1FromConfig = propsMap.get(colName + "ReplaceFrom1").toString();
                String colReplaceFrom2FromConfig = propsMap.get(colName + "ReplaceFrom2").toString();
                String colReplaceTo1FromConfig = propsMap.get(colName + "ReplaceTo1").toString();
                String colReplaceTo2FromConfig = propsMap.get(colName + "ReplaceTo2").toString();

                tableCol = patternParseOnce(selectScriptFromConfig, pattern1FromConfig, colReplaceFrom1FromConfig,
                        colReplaceFrom2FromConfig, colReplaceTo1FromConfig, colReplaceTo2FromConfig);
            }
            if (colMethodFromConfig.equals("parseScriptTwice")) {
                String selectScriptFromConfig = propsMap.get(colName + "SelectScript").toString();
                String pattern1FromConfig = propsMap.get(colName + "Pattern1").toString();
                String pattern2FromConfig = propsMap.get(colName + "Pattern2").toString();
                tableCol = parseScriptTwice(selectScriptFromConfig, pattern1FromConfig, pattern2FromConfig);
            }
        }
        return tableCol;
    }


    public static ArrayList<String> parseRow(Elements pageBlocks, String classToFind, String replaceFrom, String replaceTo) {
        tableCol.clear();
        tempString = "";
        for (Element row : pageBlocks) {
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
        return tableCol;

    }


    public static ArrayList<String> parseRowDoubleFor(Elements pageBlocks, String classToFind, String classToFind2, String classToFind3) {

        tableCol.clear();
        tempString = "";
        for (Element row : pageBlocks) {
            Elements tdddd2 = row.getElementsByClass(classToFind);
            if ((tdddd2.size() != 0) && (tdddd2.text() != null) && (!tdddd2.text().isEmpty()) && (tdddd2.text() != "")) {
                for (Element tdddd12 : page.select(classToFind2)) {
                    tempString = tdddd12.attr(classToFind3);
                    tableCol.add(tempString);
                }
            } else {
                tableCol.add(" - ");
            }
        }

        return tableCol;
    }


    public static ArrayList<String> getElementsContainingOwnText(Elements pageBlocks, String classToFind, String getElementsContainingOwnTextFromConfig, String replaceFrom, String replaceTo) {
        tableCol.clear();
        tempString = "";
        Elements pageBlocksTemp = page.select(classToFind);
        for (Element row : pageBlocksTemp) {
            Elements tdddd = row.getElementsContainingOwnText(getElementsContainingOwnTextFromConfig);
            if (tdddd.size() != 0) {
                if ((tdddd.text() != null) && (!tdddd.text().isEmpty()) && (tdddd.text() != "")) {
                    tempString = tdddd.text().replaceAll(replaceFrom, replaceTo);    //
                }
                tableCol.add(tempString);
            } else {
                tableCol.add(" - ");
            }
        }
        return tableCol;
    }


    public static ArrayList<String> getElementsMatchingOwnText(Elements pageBlocks, String classToFind, String getElementsMatchingOwnTextFromConfig, String replaceFrom, String replaceTo) {
        tableCol.clear();
        tempString = "";
        Elements pageBlocksTemp = page.select(classToFind);
        for (Element row : pageBlocksTemp) {
            Elements tdddd2 = row.getElementsMatchingOwnText(getElementsMatchingOwnTextFromConfig);
            if (tdddd2.size() != 0) {
                if ((tdddd2.text() != null) && (!tdddd2.text().isEmpty()) && (tdddd2.text() != "")) {
                    tempString = tdddd2.text().replaceAll(replaceFrom, replaceTo);
                }
                tableCol.add(tempString);
            } else {
                tableCol.add(" - ");
            }
        }
        return tableCol;
    }


    public static ArrayList<String> patternParseTwoPatterns(String selectScript, String pattern1FromConfig, String pattern2FromConfig) {

        tableCol.clear();
        String tempMatcherString1 = "";
        String tempMatcherString2 = "";
        Elements script;

        script = page.select(selectScript);

//        System.out.println(" script patternParseTwoPatterns " + script);
        String scriptString = script.toString();
//        System.out.println(" scriptString patternParseTwoPatterns " + scriptString.length() + "  " + scriptString);
        Pattern pattern1 = Pattern.compile(pattern1FromConfig);
        Pattern pattern2 = Pattern.compile(pattern2FromConfig);
        Matcher matcher1 = pattern1.matcher(scriptString);
        while (matcher1.find()) {
            tempMatcherString1 = matcher1.group();
            Matcher matcher2 = pattern2.matcher(tempMatcherString1);
            while (matcher2.find()) {
                tempMatcherString2 = matcher2.group();
                tableCol.add(tempMatcherString2);
            }
        }
        return tableCol;
    }


    public static ArrayList<String> parseScriptTwice(String selectScript, String pattern1FromConfig, String pattern2FromConfig) {
        tableCol.clear();
        String tempMatcherString1 = "";
        String tempMatcherString2 = "";

        Elements script = page.select(selectScript);
        String scriptString = script.toString();
        Pattern pattern1 = Pattern.compile(pattern1FromConfig); //  (?:\\\"contact_name\\\"\\:\\\"[а-яА-ЯёЁa-zA-Z\\s\\,]*\\\")
        Pattern pattern2 = Pattern.compile(pattern2FromConfig); // (\\\"[а-яА-ЯёЁa-zA-Z\\s\\,\\(\\)\\.]*\\\")

        Matcher matcher1 = pattern1.matcher(scriptString);
        while (matcher1.find()) {
            tempMatcherString1 = matcher1.group();
            Matcher matcher2 = pattern2.matcher(tempMatcherString1);
            while (matcher2.find()) {
                tempMatcherString2 = matcher2.group();
                tableCol.add(tempMatcherString2);
            }
        }
        return tableCol;
    }


    public static ArrayList<String> patternParseOnce(String selectScript, String pattern1FromConfig, String replaceFrom1, String replaceFrom2, String replaceTo1, String replaceTo2) {

        Elements script = page.select(selectScript);
        String scriptString = script.toString();

        tableCol.clear();
        String tempString1 = "";
        String tempString2 = "";

        Pattern pattern1 = Pattern.compile(pattern1FromConfig);
        Matcher matcher1 = pattern1.matcher(scriptString);
        while (matcher1.find()) {
            tempString1 = matcher1.group();
//            System.out.println(" tempString1 patternParseOnce " + tempString1);
            tempString2 = tempString1.replaceAll(replaceFrom1, replaceTo1);
            tempString1 = tempString2.replaceAll(replaceFrom2, replaceTo2);
            tableCol.add(tempString1);
        }
        return tableCol;
    }


}