package Models;

import Finals.Finals;
import Utils.FilesUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Daf {
    private String name;
    private int id;
    private String gemara;
    private String mishna;

    public Daf() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGemara() {
        return gemara;
    }

    public void setGemara(String gemara) {
        this.gemara = gemara;
    }

    public String getMishna() {
        return mishna;
    }

    public void setMishna(String mishna) {
        this.mishna = mishna;
    }

    @Override
    public String toString() {
        return "דף " + name + Finals.BreakLine +
                (gemara == null || gemara.isEmpty() ? "" : Finals.BreakLine + "גמרא    " + gemara + Finals.BreakLine) +
                (mishna == null || mishna.isEmpty() ? "" : Finals.BreakLine + "משנה    " + mishna + Finals.BreakLine);
    }

    public boolean writeToFile(String sourcePath) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(sourcePath + "\\content.txt", "UTF-8");
            writer.print(this);
            writer.close();
            return true;
        }
        catch (Exception ec){
            return  false;
        }

    }
}
