package ghoul.util;

import java.io.*;

public class FileManager{
    public synchronized static String read(File f){
        String temp="Illegal entries";
        try{
            if(f.exists()&&!f.isDirectory()){
                temp="";
                FileReader fr=new FileReader(f);
                int n=0;
                while((n=fr.read())!=-1)temp+=(char)n;}
            else 
                throw new IOException("File not found : "+f.toString());
        }catch(IOException e){e.printStackTrace();}
        return temp;
    }
    public static String read(String filename){
        return read(new File(filename));
    }
    public static int write(File f,String content){
        int ctr=0;
        try{
            FileWriter fw=new FileWriter(f);
            fw.write(content);
            fw.flush();fw.close();
            ctr=1;
        }catch(IOException e){e.printStackTrace();}
        return ctr;
    }
    public static int write(String filename,String content){
        return write(new File(filename),content);
    }
    /*public static void main(String[] args){
        System.out.println(read("ServerSample.java"));
    }*/
}
