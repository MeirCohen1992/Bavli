package Models;

import Utils.FilesUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Series {
    private String name;
    private int id;
    private ArrayList<Masehet> masehtot;
    private String sourcePath;

    public Series() {
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

    public ArrayList<Masehet> getMasehtot() {
        return masehtot;
    }

    public void setMasehtot(ArrayList<Masehet> masehtot) {
        this.masehtot = masehtot;
    }


    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    @Override
    public String toString() {
        return "Series{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", masehtot=" + masehtot +
                ", sourcePath='" + sourcePath + '\'' +
                '}';
    }

    public Masehet findMasehetByName(String name) {
        for (int i = 0; i < masehtot.toArray().length; i++) {
            if (masehtot.get(i).getName().equals(name)) {
                return masehtot.get(i);
            }
        }
        return null;
    }

    public String getfolder() {
        File baseFile = new File(sourcePath);
        return baseFile.isDirectory() ? sourcePath : baseFile.getParent();
    }

    public boolean writeToFile() throws FileNotFoundException {
        boolean successAll = true;
        for (int i = 0; i < masehtot.toArray().length; i++) {
            String path = new File(getfolder(), FilesUtils.fixName(masehtot.get(i).getName())).getPath();
            if (!FilesUtils.createFolder(path)) {
                successAll = false;
            }
            if(!masehtot.get(i).writeToFile(path)){
                successAll = false;
            }
        }
        return successAll;
    }
}
