package ghoul.lib;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

class ConfigFile{
    private File file;
    private int port;
    private String uid,pswd;
    public ConfigFile(File f){
        if(file.exists()){
           file=f;
        }
        else{
          System.out.println("No config file");
          return;
        }
    }
    public ConfigFile(String str){
        this(new File(str));
    }
    public boolean create(){
        boolean n=false;
        try{
        n=file.createNewFile();
        }catch(IOException e){e.printStackTrace();}
        return n;
    }
    public int getPort(){return this.port;}
    public String getUser(){return this.uid;}
    public String getPassword(){return this.pswd;}
    
}
