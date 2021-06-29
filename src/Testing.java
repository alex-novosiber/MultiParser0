import java.util.ArrayList;

public class Testing {


    public static ArrayList<String> tableCol0 = new ArrayList<String>();

    static int rrr;
    static boolean col1Use = true;

    public Testing() {
        rrr = 250;
    }

    public static void main(String[] args) {


        Testing tstng = new Testing();
        System.out.println(" rrr = " + tstng.rrr);


//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("chrome.switches", "--disable-extensions");
//        options.addArguments("--disable-save-password");
//        options.addArguments("disable-infobars");
//        options.setBinary("Chrome\\Application\\chrome.exe");
//        options.addArguments("--headless");
//
//        System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver_win32_FOR__Chrome_v81.exe");
//
//
//        WebDriver driver = new ChromeDriver(options);
//
////        Document doc = Jsoup.parse(driver.getPageSource());
//
//        String url = "https://novosibirsk.n1.ru/kupit/kvartiry/?has_photos=true&author=owner&limit=25";
//        driver.get(url);
//        Document dc = null;
//        String htmlDC = driver.getPageSource();
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//        String dc0000 = htmlDC.toString();
//        dc = Jsoup.parse(dc0000);
//
//        try {
//            TempFileWriter.write(dc.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //Pattern patternExample = Pattern.compile("\"contact_name\":\"(.)*\",");
//        if (1 == 1) {
//            String scriptData =
//                    "                \"contact_name\":\"Вячеслав\",\n" +
//                            "                \"contact_name\":\"Людмила\",\n" +
//                            "                \"contact_name\":\"Антон\",\n" +
//                            "                \"contact_name\":\"Владимир (собственник)\",\n" +
//                            "                \"contact_name\":\"Алексей Николаевич\",\n" +
//                            "                \"contact_name\":\"Наталья\",\n" +
//                            "                \"contact_name\":\"Юлия\",\n" +
//                            "                \"contact_name\":\"Ксения\",\n" +
//                            "                \"contact_name\":\"Юлия\",\n" +
//                            "                \"contact_name\":\"Вячеслав\",\n" +
//                            "                \"contact_name\":\"Виктория\",\n" +
//                            "                \"contact_name\":\"Сергей Алексеевич\",\n" +
//                            "                \"contact_name\":\"Сергей Алексеевич\",\n" +
//                            "                \"contact_name\":\"Сергей Алексеевич\",\n" +
//                            "                \"contact_name\":\"Сергей Алексеевич\",\n" +
//                            "                \"contact_name\":\"Константин\",\n" +
//                            "                \"contact_name\":\"Дмитрий \",\n" +
//                            "                \"contact_name\":\"Лана\",\n" +
//                            "                \"contact_name\":\"Олег\",\n" +
//                            "                \"contact_name\":\"Влад\",\n" +
//                            "                \"contact_name\":\"Николай\",\n" +
//                            "                \"contact_name\":\"Сергей Александрович\",\n" +
//                            "                \"contact_name\":\"Татьяна\",\n" +
//                            "                \"contact_name\":\"Наталья - \\ Fykdh\\\",\n" +
//                            "                \"contact_name\":\"Светлана DH 255 ( ) / _ . , - + \\\",";
//
//
//            Pattern patternExample = Pattern.compile("\"contact_name\":\"(.)*\",");
//            Matcher matcherExample = patternExample.matcher(scriptData);
////  System.out.println(" patternExample = " + patternExample);
//            int counter = 0;
//
//            while (matcherExample.find()) {
//                String tempString = matcherExample.group();
//                counter++;
////  System.out.println(counter + " " + tempString);
//            }
//
//
//        }


    }
}