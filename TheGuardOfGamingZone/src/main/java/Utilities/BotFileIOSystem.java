package Utilities;

import java.io.*;
import java.nio.file.Path;

public class BotFileIOSystem {
    private final String basePath;

    public BotFileIOSystem(String path){
        System.out.println(path);
        basePath = path;
        File tmp = new File(path);
        if(!(tmp.exists() && tmp.isDirectory())){
            tmp.mkdir();
        }
    }

    public boolean override(File file, String str) throws IOException {
        File file2 = new File(basePath+ file.getPath());
        if(!file2.exists()){
            if(!file2.createNewFile()){
                return false;
            }
        }
        if(file2.canWrite()){
            FileWriter fw = new FileWriter(file2);
            fw.write(str);
            fw.close();
            return true;
        }
        return false;
    }

    public String load(File file) throws IOException {
        File file2 = new File(basePath+ file.getPath());
        if(file2.exists()&&file2.canRead()){
            StringBuilder builder = new StringBuilder();
            FileReader fr = new FileReader(file2);

            int ch;
            while((ch = fr.read()) != -1){
                builder.append((char) ch);
            }
            return builder.toString();
        }
        return null;
    }
}
