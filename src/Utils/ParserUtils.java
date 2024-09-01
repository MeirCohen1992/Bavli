package Utils;

import Finals.Finals;
import Models.Daf;
import Models.Masehet;
import Models.Perek;
import Models.Series;

import java.util.ArrayList;
import java.util.Collections;

public class ParserUtils {


    public static Series parseSeries(String content, String path) {
        String[] lines = cleanString(content).split(Finals.BreakLine);
        if (lines.length > 0) {
            Series series = new Series();
            series.setName(lines[0]);
            series.setId(1);
            series.setSourcePath(path);
            series.setMasehtot(parseMasehtot(content));
            return series;
        }
        return null;
    }

    public static ArrayList<Masehet> parseMasehtot(String content) {
        ArrayList<Masehet> masehets = new ArrayList<>();
        String[] m = content.split(Finals.Masehet);
        int masehetIndex = 1;
        for (int i = 1; i < m.length - 1; i++) {
            String perekContent = cleanString(m[i]);
            String name = getFirstWord(perekContent);

            Masehet masehet = findMasehet(masehets, name);
            if (masehet == null && name != null) {
                masehet = new Masehet();
                masehet.setId(masehetIndex++);
                masehet.setName(name);
                masehets.add(masehet);
            }

            Perek perek = parsePerek(perekContent, name);
            if (perek != null) {
                masehet.addPerek(perek);
            }
        }
        //Collections.sort(masehets);
        return masehets;
    }

    private static Masehet findMasehet(ArrayList<Masehet> masehtot, String name) {

        for (int i = 0; i < masehtot.toArray().length; i++) {
            if (masehtot.get(i).getName().equals(name)) {
                return masehtot.get(i);
            }
        }
        return null;
    }

    public static Perek parsePerek(String content, String masehetName) {
        Perek perek = new Perek();
        String[] p = content.split(Finals.Perek);
        if (p.length > 1) {
            String name = getAllLine(cleanString(p[1]));
            perek.setMasehetName(masehetName);
            perek.setName(name);
            perek.setPages(parsePages(content));
            return perek;
        }
        return null;
    }

    public static ArrayList<Daf> parsePages(String content) {
        ArrayList<Daf> pages = new ArrayList<>();
        String[] p = content.split(Finals.Daf);
        int dafIndex = 1;
        for (int i = 1; i < p.length; i++) {
            String dafContent = cleanString(p[i]);
            String name = getAllLine(content);

            Daf daf = findDaf(pages, name);
            if (daf == null) {
                daf = new Daf();
                daf.setId(dafIndex++);
                daf.setName(name);
                pages.add(daf);
            }

            String[] arr = content.split(Finals.Gmara);
            if (arr.length > 1) {
                daf.setGemara(cleanString(arr[1]));
            }

            arr = content.split(Finals.Mishna);
            if (arr.length > 1) {
                daf.setMishna(cleanString(arr[1]));
            }
        }
        return pages;
    }

    public static Daf findDaf(ArrayList<Daf> pages, String name) {
        for (int i = 0; i < pages.toArray().length; i++) {
            if (pages.get(i).getName().equals(name)) {
                return pages.get(i);
            }
        }
        return null;
    }

    private static String cleanString(String str) {
        return str.trim().replace("  ", " ");
    }

    private static String getFirstWord(String content) {
        String[] arr = content.split(" ");
        if (arr.length > 0) {
            return arr[0];
        }
        return null;
    }

    private static String getAllLine(String content) {
        String[] arr = content.split(Finals.BreakLine);
        if (arr.length > 0) {
            return arr[0];
        }
        return null;
    }
}
