package Utils;

import java.io.File;

public class FilesUtils {

    public static boolean createFolder(String path) {
        File folderPath = new File(path);
        if (!folderPath.exists()) {
            return folderPath.mkdir();
        }
        return true;
    }

    public static String fixName(String name) {
        if(name == null){
return  "";
        }
        return name.trim().replace(" ", "_");
    }
}
