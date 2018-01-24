package ghoul.lib;

import java.io.*;

import java.util.Date;

public class LogFile{
    FileReader fr;
    String port,user,passphrase,currentDB;
    public LogFile(){
        
    }
    public void create(File f){
        
    }
    public void create(String dir){create(new File(dir));}
}
