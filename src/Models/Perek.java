package Models;

import Finals.Finals;
import Utils.FilesUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Perek {
    private String masehetName;
    private String name;
    private int id;
    private ArrayList<Daf> pages = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getMasehetName() {
        return masehetName;
    }

    public void setMasehetName(String masehetName) {
        this.masehetName = masehetName;
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

    public ArrayList<Daf> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Daf> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        String content = "מסכת " + this.getMasehetName() +
                " פרק " + this.getName() + Finals.BreakLine;
        for (int i = 0; i < pages.toArray().length; i++) {
            content += pages.get(i) + Finals.BreakLine;
        }
        return content;
    }

    public boolean writeToFile(String sourcePath) throws FileNotFoundException {
        boolean successAll = true;
        for (int i = 0; i < pages.toArray().length; i++) {
            String path = new File(sourcePath, FilesUtils.fixName(pages.get(i).getName())).getPath();
            if (!FilesUtils.createFolder(path)) {
                successAll = false;
            }
            if (!pages.get(i).writeToFile(path)) {
                successAll = false;
            }
        }
        return successAll;
    }
}
