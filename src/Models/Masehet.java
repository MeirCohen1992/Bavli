package Models;


import Finals.Finals;
import Utils.FilesUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Masehet implements Comparable<Masehet> {
    private String name;
    private int id;
    private ArrayList<Perek> pereks = new ArrayList<>();


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

    public ArrayList<Perek> getPereks() {
        return pereks;
    }

    public void setPereks(ArrayList<Perek> pereks) {
        this.pereks = pereks;
    }

    public void addPerek(Perek perek) {
        this.pereks.add(perek);
    }

    @Override
    public String toString() {
        String content = "";
        for (int i = 0; i < this.pereks.toArray().length; i++) {
            content += Finals.BreakLine + this.pereks.get(i).toString() + Finals.BreakLine;
        }
        return content;
    }

    public boolean writeToFile(String sourcePath) throws FileNotFoundException {
        boolean successAll = true;
        for (int i = 0; i < pereks.toArray().length; i++) {
            String path = new File(sourcePath, FilesUtils.fixName(pereks.get(i).getName())).getPath();
            if (!FilesUtils.createFolder(path)) {
                successAll = false;
            }
            if(!pereks.get(i).writeToFile(path)){
                successAll = false;
            }
        }
        return successAll;
    }

    @Override
    public int compareTo(Masehet o) {
        return o.name.charAt(0);
    }
}
