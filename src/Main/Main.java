package Main;

import Models.Series;
import Utils.ParserUtils;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    static Series series;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String path = "C:\\Meir\\bavliProject\\‎bavli.txt";
        try {
            String content = Files.readString(Path.of(path), StandardCharsets.UTF_8);

            while (true) {
                System.out.println("הכנס 1 להצגת מסכת, הכנס 2 לחיפוש ציטוט, הכנס 3 כדי ליצור קבצים למסכת");
                int choose = scan.nextInt();
                scan.nextLine();
                switch (choose) {
                    case 1:
                        System.out.println("הכנס שם מסכת");
                        String name = scan.nextLine().trim();
                        System.out.println("הכנס שם דף");
                        String nameDaf = scan.nextLine().trim();

                        parseMasehet(content, name, nameDaf);
                        break;
                    case 2:
                        System.out.println("הכנס ציטוט");
                        String text = scan.nextLine().trim();

                        showText(content, text);
                        break;
                    case 3:
                        splitFiles(content, path);
                        break;
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void splitFiles(String fileContent, String path) throws FileNotFoundException {
        Series bavli = ParserUtils.parseSeries(fileContent, path);
        series = bavli;
        boolean success = bavli.writeToFile();
        if (!success) {
            System.out.println("error not known");
        }
    }

    private static void showText(String fileContent, String text) {
        if (fileContent.contains(text)) {
            System.out.println("found text");
        } else {
            System.out.println("not found");
        }
    }

    private static void parseMasehet(String fileContent, String masehetName, String nameDaf) {
        String name = "מסכת " + masehetName;
        String daf = "דף " + nameDaf;
        int startIndex = fileContent.indexOf(name);
        int endIndex = fileContent.lastIndexOf(name);
        if (startIndex == -1) {
            System.out.println("not found masehet");
        } else {
            String masehetContent = fileContent.substring(startIndex, endIndex);
            System.out.println(getDafContent(masehetContent, daf));
        }
    }

    private static String getDafContent(String content, String search) {
        String s = "";
        int index = content.indexOf(search);
        if (index == -1) {
            System.out.println("not found");
        }
        while (index != -1) {
            int length = content.substring(index + search.length()).indexOf("דף ");
            length =  length == -1 ? content.length(): length + search.length();
            s += " " + content.substring(index, index + length -1);
            content = content.substring(index + search.length());
            index = content.indexOf(search);
        }
        return s;
    }
}
